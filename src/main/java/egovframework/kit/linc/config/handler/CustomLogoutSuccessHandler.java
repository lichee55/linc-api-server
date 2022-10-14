package egovframework.kit.linc.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import egovframework.kit.linc.cmm.ResponseForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        httpServletResponse.setStatus(200);
        httpServletResponse.setContentType("application/json;charset=UTF-8");

        ResponseForm<String> responseForm = new ResponseForm<>(null, "로그아웃 성공", 200);

        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(responseForm));


    }
}
