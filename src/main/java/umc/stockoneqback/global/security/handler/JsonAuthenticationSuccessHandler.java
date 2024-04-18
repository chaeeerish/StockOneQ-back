package umc.stockoneqback.global.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import umc.stockoneqback.auth.domain.model.jwt.AuthToken;
import umc.stockoneqback.auth.service.dto.response.LoginResponse;
import umc.stockoneqback.auth.service.jwt.TokenIssuer;
import umc.stockoneqback.auth.utils.TokenResponseWriter;
import umc.stockoneqback.global.security.principle.UserPrincipal;

import java.io.IOException;

@RequiredArgsConstructor
public class JsonAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final TokenIssuer tokenIssuer;
    private final TokenResponseWriter tokenResponseWriter;
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final Authentication authentication
    ) throws IOException {
        final UserPrincipal user = getPrincipal(authentication);
        final AuthToken authToken = tokenIssuer.provideAuthorityToken(user.id());

        tokenResponseWriter.applyToken(response, authToken);
        sendResponse(response, user);
    }

    private UserPrincipal getPrincipal(final Authentication authentication) {
        return (UserPrincipal) authentication.getPrincipal();
    }

    private void sendResponse(final HttpServletResponse response, final UserPrincipal user) throws IOException {
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        objectMapper.writeValue(response.getWriter(), new LoginResponse(user.id(), user.loginId(), user.name()));
    }
}
