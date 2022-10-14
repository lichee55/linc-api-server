package egovframework.kit.linc.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import egovframework.kit.linc.cmm.ResponseForm;
import egovframework.kit.linc.security.LoginDTO;
import egovframework.kit.linc.security.RoleMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RequiredArgsConstructor
@Component
@Slf4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        HttpSession session = httpServletRequest.getSession();
        session.setMaxInactiveInterval(60 * 60 * 3);


        LoginDTO loginUser = null;
        if (authentication.getPrincipal() instanceof RoleMember) {
            RoleMember principal = (RoleMember) authentication.getPrincipal();

            loginUser = LoginDTO.builder()
                    .loginId(principal.getUsername())
                    .name(principal.getName())
                    .role(principal.getAuthorities().toString())
                    .build();

        } else {
            loginUser = LoginDTO.builder()
                    .loginId(authentication.getName())
                    .name(authentication.getName())
                    .role(authentication.getAuthorities().toString())
                    .build();
        }

        ResponseForm<LoginDTO> responseForm = new ResponseForm<>(loginUser, "success", 200);
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(responseForm));
    }
}