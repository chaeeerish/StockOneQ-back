package umc.stockoneqback.auth.domain.model.jwt;

public record AuthToken(
        String accessToken,
        String refreshToken
) {
}
