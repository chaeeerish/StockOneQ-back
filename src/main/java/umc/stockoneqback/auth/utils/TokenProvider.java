package umc.stockoneqback.auth.utils;

public interface TokenProvider {
    String createAccessToken(final Long userId);
    String createRefreshToken(final Long userId);
    Long getId(final String token);
    void validateToken(final String token);
}