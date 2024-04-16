package umc.stockoneqback.global.security.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import umc.stockoneqback.auth.exception.AuthErrorCode;
import umc.stockoneqback.global.security.exception.SecurityJwtAccessDeniedException;

public class JwtLogoutTokenCheckHandler implements LogoutHandler {
    @Override
    public void logout(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final Authentication authentication
    ) {
        if (authentication == null) {
            throw SecurityJwtAccessDeniedException.type(AuthErrorCode.INVALID_PERMISSION);
        }
    }
}
