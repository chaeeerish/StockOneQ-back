package umc.stockoneqback.auth.service.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.stockoneqback.auth.domain.model.jwt.AuthToken;
import umc.stockoneqback.auth.utils.TokenProvider;

@Component
@RequiredArgsConstructor
public class JwtTokenIssuer {
    private final TokenProvider tokenProvider;
    private final JwtTokenManager jwtTokenManager;

    public AuthToken provideAuthorityToken(final Long userId) {
        final String accessToken = tokenProvider.createAccessToken(userId);
        final String refreshToken = tokenProvider.createRefreshToken(userId);
        jwtTokenManager.synchronizeRefreshToken(userId, refreshToken);

        return new AuthToken(accessToken, refreshToken);
    }

    public AuthToken reissueAuthorityToken(final Long userId) {
        final String newAccessToken = tokenProvider.createAccessToken(userId);
        final String newRefreshToken = tokenProvider.createRefreshToken(userId);
        jwtTokenManager.updateRefreshToken(userId, newRefreshToken);

        return new AuthToken(newAccessToken, newRefreshToken);
    }

    public boolean isUserRefreshToken(final Long userId, final String refreshToken) {
        return jwtTokenManager.isUserRefreshToken(userId, refreshToken);
    }

    public void deleteRefreshToken(final Long userId) {
        jwtTokenManager.deleteRefreshToken(userId);
    }
}