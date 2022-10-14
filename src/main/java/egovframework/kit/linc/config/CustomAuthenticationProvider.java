package egovframework.kit.linc.config;

import egovframework.kit.linc.security.UserDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailServiceImpl userDetailService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        UserDetails userDetails = userDetailService.loadUserByUsername(authentication.getName());
        String pw = authentication.getCredentials().toString();

        if (!passwordEncoder.matches(pw, userDetails.getPassword())) {
            throw new BadCredentialsException("비밀번호가 틀립니다.");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, pw, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
