package umc.stockoneqback.auth.service.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.stockoneqback.auth.domain.model.jwt.AuthToken;
import umc.stockoneqback.auth.dto.request.ReissueTokenRequest;
import umc.stockoneqback.auth.exception.AuthErrorCode;
import umc.stockoneqback.auth.utils.TokenProvider;
import umc.stockoneqback.global.exception.BaseException;

@Service
@RequiredArgsConstructor
public class TokenReissueService {
    private final TokenProvider tokenProvider;
    private final TokenIssuer tokenIssuer;

    public AuthToken invoke(final String refreshToken) {
        final Long userId = tokenProvider.getId(refreshToken);
        validateUserToken(userId, refreshToken);
        return tokenIssuer.reissueAuthorityToken(userId);
    }

    private void validateUserToken(final Long userId, final String refreshToken) {
        if (isAnonymousRefreshToken(userId, refreshToken)) {
            throw BaseException.type(AuthErrorCode.AUTH_INVALID_TOKEN);
        }
    }

    private boolean isAnonymousRefreshToken(final Long userId, final String refreshToken) {
        return !tokenIssuer.isUserRefreshToken(userId, refreshToken);
    }
}