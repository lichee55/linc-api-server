package egovframework.kit.linc.config;

import java.net.MalformedURLException;
import java.util.List;

import egovframework.kit.linc.cmm.ResponseForm;
import egovframework.kit.linc.config.handler.CustomAuthenticationFailureHandler;
import egovframework.kit.linc.config.handler.CustomAuthenticationSuccessHandler;
import egovframework.kit.linc.config.handler.CustomLogoutSuccessHandler;
import egovframework.kit.linc.security.UserDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final ObjectMapper objectMapper;
    private final UserDetailServiceImpl userDetailsService;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    private final CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    private static final String[] PERMIT_URL_ARRAY = {
            /* sign in, sign up */
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/**",
            "/api/member/login",
            "/api/member/logout",
            "/api/member/new",
    };

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, CorsConfigurationSource corsConfigurationSource, AuthenticationProvider provider) throws Exception {

        http
                .httpBasic().disable();

        http
                .cors()
                .configurationSource(corsConfigurationSource)
                .and()
                .csrf()
                .disable()
                .headers()
                .disable()
                .httpBasic()
                .disable()
                .rememberMe()
                .disable();

        http
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers(PERMIT_URL_ARRAY).permitAll()
                .antMatchers("/api/company/**").hasAnyRole("ADMIN", "COMPANY", "OFFICER")
                .anyRequest().authenticated();

        http
                .userDetailsService(userDetailsService)
                .userDetailsService(inMemoryUserDetailsManager())
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)
                .and()
                .sessionFixation().changeSessionId()
                .invalidSessionStrategy((request, response) -> {
                    response.setStatus(401);
                    response.setContentType("application/json;charset=UTF-8");
                    ResponseForm<String> responseForm = new ResponseForm<>(null, "세션 정보가 유효하지 않습니다.", 401);
                    response.getWriter().write(objectMapper.writeValueAsString(responseForm));
                })
                .sessionAuthenticationFailureHandler((request, response, exception) -> {
                    response.setStatus(401);
                    response.setContentType("application/json;charset=UTF-8");
                    ResponseForm<String> responseForm = new ResponseForm<>(null, "세션 인증에 실패했습니다.", 401);
                    response.getWriter().write(objectMapper.writeValueAsString(responseForm));
                });

        http
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> {
                    response.setStatus(401);
                    response.setContentType("application/json;charset=UTF-8");
                    ResponseForm<String> responseForm = new ResponseForm<>(null, "로그인이 필요합니다.", 401);
                    response.getWriter().write(objectMapper.writeValueAsString(responseForm));
                })
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    response.setStatus(403);
                    response.setContentType("application/json;charset=UTF-8");
                    ResponseForm<String> responseForm = new ResponseForm<>(null, "접근 권한이 없습니다.", 403);
                    response.getWriter().write(objectMapper.writeValueAsString(responseForm));
                });

        http
                .formLogin()
                .loginProcessingUrl("/api/member/login")
                .usernameParameter("loginId")
                .passwordParameter("password")
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler)
                .and()
                .logout()
                .logoutUrl("/api/member/logout")
                .logoutSuccessHandler(customLogoutSuccessHandler)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");

        http
                .authenticationProvider(provider);

        return http.build();
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web -> web.ignoring().antMatchers("/images/**"));
    }


    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        UserDetails company = User.withUsername("company")
                .password(getPasswordEncoder().encode("company"))
                .roles("COMPANY")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password(getPasswordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        UserDetails officer = User.withUsername("officer")
                .password(getPasswordEncoder().encode("officer"))
                .roles("OFFICER")
                .build();

        UserDetails professor = User.withUsername("professor")
                .password(getPasswordEncoder().encode("professor"))
                .roles("PROFESSOR")
                .build();


        return new InMemoryUserDetailsManager(company, admin, officer, professor);
    }

    @Bean
    public CookieSerializer cookieSerializer() throws MalformedURLException {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setCookieName("JSESSIONID");
        serializer.setSameSite("");
        serializer.setUseHttpOnlyCookie(true);
        return serializer;
    }
}
