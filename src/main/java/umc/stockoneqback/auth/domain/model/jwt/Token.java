package umc.stockoneqback.auth.domain.model.jwt;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "token")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false, updatable = false, unique = true)
    private Long userId;

    @Column(name = "refresh_token", nullable = false, unique = true)
    private String refreshToken;

    private Token(final Long userId, final String refreshToken) {
        this.userId = userId;
        this.refreshToken = refreshToken;
    }

    public static Token issueRefreshToken(final Long userId, final String refreshToken) {
        return new Token(userId, refreshToken);
    }

    public void updateRefreshToken(final String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
