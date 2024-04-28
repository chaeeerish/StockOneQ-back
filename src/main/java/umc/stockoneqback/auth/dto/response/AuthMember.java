package umc.stockoneqback.auth.dto.response;

import umc.stockoneqback.auth.domain.model.jwt.AuthToken;
import umc.stockoneqback.user.domain.User;

public record AuthMember(
        Long userId,
        String loginId,
        String name,
        String accessToken,
        String refreshToken
) {
    public static AuthMember of(final User user, final AuthToken token) {
        return new AuthMember(user.getId(), user.getLoginId(), user.getName(), token.accessToken(), token.refreshToken());
    }
}