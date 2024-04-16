package umc.stockoneqback.auth.service.dto.response;

import umc.stockoneqback.auth.domain.model.AuthToken;
import umc.stockoneqback.user.domain.User;

public record AuthMember(
        Long id,
        String loginId,
        String name,
        String accessToken,
        String refreshToken
) {
    public static AuthMember of(final User user, final AuthToken token) {
        return new AuthMember(user.getId(), user.getLoginId(), user.getName(), token.accessToken(), token.refreshToken());
    }
}