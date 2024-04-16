package umc.stockoneqback.auth.service.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.stockoneqback.auth.domain.model.AuthToken;
import umc.stockoneqback.auth.service.fcm.FcmTokenService;
import umc.stockoneqback.auth.service.dto.response.AuthMember;
import umc.stockoneqback.global.exception.BaseException;
import umc.stockoneqback.user.domain.Password;
import umc.stockoneqback.user.domain.User;
import umc.stockoneqback.user.exception.UserErrorCode;
import umc.stockoneqback.user.service.UserFindService;

import static umc.stockoneqback.global.utils.PasswordEncoderUtils.ENCODER;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {
    private final UserFindService userFindService;
    private final TokenIssuer tokenIssuer;
    private final FcmTokenService fcmTokenService;

    @Transactional
    public AuthMember login(String loginId, String password) {
        User user = userFindService.findByLoginId(loginId);
        validatePassword(password, user.getPassword());

        AuthToken authToken = tokenIssuer.provideAuthorityToken(user.getId());
        return AuthMember.of(user, authToken);
    }

    private void validatePassword(String comparePassword, Password saved) {
        if(!saved.isSamePassword(comparePassword, ENCODER)) {
            throw BaseException.type(UserErrorCode.WRONG_PASSWORD);
        }
    }

    @Transactional
    public void logout(Long userId) {
        tokenIssuer.deleteRefreshToken(userId);
        fcmTokenService.deleteFcmToken(userId);
    }

    @Transactional
    public void saveFcm(Long userId, String token) {
        fcmTokenService.saveFcmToken(userId, token);
    }
}
