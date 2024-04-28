package umc.stockoneqback.auth.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.stockoneqback.auth.domain.model.jwt.AuthToken;
import umc.stockoneqback.auth.domain.model.jwt.TokenType;
import umc.stockoneqback.auth.dto.request.ReissueTokenRequest;
import umc.stockoneqback.auth.service.jwt.TokenReissueService;
import umc.stockoneqback.auth.utils.TokenResponseWriter;
import umc.stockoneqback.global.annotation.ExtractToken;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/token/reissue")
public class TokenReissueApiController {
    private final TokenReissueService tokenReissueService;
    private final TokenResponseWriter tokenResponseWriter;

    @PostMapping
    public ResponseEntity<Void> reissueTokens(@ExtractToken(tokenType = TokenType.REFRESH) final String refreshToken,
                                              final HttpServletResponse response) {
        final AuthToken authToken = tokenReissueService.invoke(new ReissueTokenRequest(refreshToken));
        tokenResponseWriter.applyToken(response, authToken);

        return ResponseEntity.noContent().build();
    }
}
