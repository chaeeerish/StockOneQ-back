package umc.stockoneqback.auth.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import umc.stockoneqback.auth.exception.AuthErrorCode;
import umc.stockoneqback.global.exception.BaseException;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider implements TokenProvider {
    private final SecretKey secretKey;
    private final long accessTokenValidityInMilliseconds;
    private final long refreshTokenValidityInMilliseconds;

    public JwtTokenProvider(@Value("${jwt.secret}") final String secretKey,
                            @Value("${jwt.access-token-validity}") final long accessTokenValidityInMilliseconds,
                            @Value("${jwt.refresh-token-validity}") final long refreshTokenValidityInMilliseconds) {
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.accessTokenValidityInMilliseconds = accessTokenValidityInMilliseconds;
        this.refreshTokenValidityInMilliseconds = refreshTokenValidityInMilliseconds;
    }

    @Override
    public String createAccessToken(Long userId) {
        return createToken(userId, accessTokenValidityInMilliseconds);
    }

    @Override
    public String createRefreshToken(Long userId) {
        return createToken(userId, refreshTokenValidityInMilliseconds);
    }

    private String createToken(Long userId, long validityInMilliseconds) {
        Claims claims = Jwts.claims();
        claims.put("id", userId);

        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime tokenValidity = now.plusSeconds(validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(now.toInstant()))
                .setExpiration(Date.from(tokenValidity.toInstant()))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public Long getId(String token) {
        return getClaims(token)
                .getBody()
                .get("id", Long.class);
    }

    @Override
    public void validateToken(final String token) {
        try {
            final Jws<Claims> claims = getClaims(token);
            final Date expiredDate = claims.getBody().getExpiration();
            final Date now = new Date();

            if (expiredDate.before(now)) {
                throw BaseException.type(AuthErrorCode.AUTH_EXPIRED_TOKEN);
            }
        } catch (final ExpiredJwtException |
                       SecurityException |
                       MalformedJwtException |
                       UnsupportedJwtException |
                       IllegalArgumentException e) {
            throw BaseException.type(AuthErrorCode.AUTH_INVALID_TOKEN);
        }
    }

    private Jws<Claims> getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token);
    }
}
