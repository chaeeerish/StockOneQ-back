package umc.stockoneqback.auth.domain.model;

public record AuthToken(
        String accessToken,
        String refreshToken
) {
}
