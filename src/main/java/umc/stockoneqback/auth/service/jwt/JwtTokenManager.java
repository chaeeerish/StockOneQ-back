package umc.stockoneqback.auth.service.jwt;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.stockoneqback.auth.domain.model.jwt.Token;
import umc.stockoneqback.auth.domain.repository.TokenRepository;

@Component
@RequiredArgsConstructor
public class JwtTokenManager {
    private final TokenRepository tokenRepository;

    @Transactional
    public void synchronizeRefreshToken(final Long userId, final String refreshToken) {
        tokenRepository.findByUserId(userId)
                .ifPresentOrElse(
                        token -> token.updateRefreshToken(refreshToken),
                        () -> tokenRepository.save(Token.issueRefreshToken(userId, refreshToken))
                );
    }

    public void updateRefreshToken(final Long userId, final String newRefreshToken) {
        tokenRepository.updateRefreshToken(userId, newRefreshToken);
    }

    public void deleteRefreshToken(final Long userId) {
        tokenRepository.deleteRefreshToken(userId);
    }

    public boolean isUserRefreshToken(final Long userId, final String refreshToken) {
        return tokenRepository.existsByUserIdAndRefreshToken(userId, refreshToken);
    }
}