package umc.stockoneqback.global.security.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import umc.stockoneqback.global.exception.BaseException;
import umc.stockoneqback.global.exception.GlobalErrorCode;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
        throw BaseException.type(GlobalErrorCode.INVALID_USER); // 필요한 권한이 존재하지 않는 경우 403
    }
}
