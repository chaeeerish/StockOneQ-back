package umc.stockoneqback.auth.service.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.stockoneqback.auth.controller.dto.request.ReissueTokenRequest;
import umc.stockoneqback.auth.domain.model.jwt.AuthToken;
import umc.stockoneqback.auth.exception.AuthErrorCode;
import umc.stockoneqback.auth.utils.TokenProvider;
import umc.stockoneqback.global.exception.BaseException;

@Service
@RequiredArgsConstructor
public class JwtTokenReissueService {
    private final TokenProvider tokenProvider;
    private final JwtTokenIssuer jwtTokenIssuer;

    public AuthToken invoke(final ReissueTokenRequest request) {
        final Long userId = tokenProvider.getId(request.refreshToken());
        validateUserToken(userId, request.refreshToken());
        return jwtTokenIssuer.reissueAuthorityToken(userId);
    }

    private void validateUserToken(final Long userId, final String refreshToken) {
        if (isAnonymousRefreshToken(userId, refreshToken)) {
            throw BaseException.type(AuthErrorCode.AUTH_INVALID_TOKEN);
        }
    }

    private boolean isAnonymousRefreshToken(final Long userId, final String refreshToken) {
        return !jwtTokenIssuer.isUserRefreshToken(userId, refreshToken);
    }
}