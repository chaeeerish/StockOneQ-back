package umc.stockoneqback.auth.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import umc.stockoneqback.auth.domain.model.fcm.FcmToken;
import umc.stockoneqback.auth.service.fcm.FcmTokenService;
import umc.stockoneqback.common.ServiceTest;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static umc.stockoneqback.fixture.TokenFixture.FCM_TOKEN;

@DisplayName("Auth [Service Layer] -> TokenService 테스트")
class RefreshFcmTokenServiceTest extends ServiceTest {
    @Autowired
    private FcmTokenService fcmTokenService;

    private final Long USER_ID = 1L;

    @Test
    @DisplayName("현재 저장된 사용자의 FCM Token 정보를 갱신한다")
    void saveFcmToken() {
        // given
        fcmTokenRedisRepository.save(FcmToken.createFcmToken(USER_ID, FCM_TOKEN));

        // when
        final String newFcmToken = FCM_TOKEN + "_new";
        fcmTokenService.saveFcmToken(USER_ID, newFcmToken);

        // then
        FcmToken findToken = fcmTokenRedisRepository.findById(USER_ID).orElseThrow();
        assertThat(findToken.getToken()).isEqualTo(newFcmToken);
    }

    @Test
    @DisplayName("현재 저장된 사용자의 FCM Token 정보를 삭제한다")
    void deleteFcmToken() {
        // given
        fcmTokenRedisRepository.save(FcmToken.createFcmToken(USER_ID, FCM_TOKEN));

        // when
        fcmTokenService.deleteFcmToken(USER_ID);

        // then
        assertThat(fcmTokenRedisRepository.findById(USER_ID)).isEmpty();
    }

    @Test
    @DisplayName("현재 접속중인 모든 사용자의 FCM Token 정보를 불러온다")
    void findAllOnlineUsers() {
        // given
        final Long SECOND_USER_ID = 2L;
        final String SECOND_USER_FCM_TOKEN = "example_refresh_token_2";
        fcmTokenRedisRepository.save(FcmToken.createFcmToken(USER_ID, FCM_TOKEN));
        fcmTokenRedisRepository.save(FcmToken.createFcmToken(SECOND_USER_ID, SECOND_USER_FCM_TOKEN));

        // when
        List<FcmToken> fcmTokenList = fcmTokenService.findAllOnlineUsers();
        fcmTokenList.sort(Comparator.comparing(FcmToken::getId));

        // then
        assertAll(
                () -> assertThat(fcmTokenList.size()).isEqualTo(2),
                () -> assertThat(fcmTokenList.get(0).getId()).isEqualTo(USER_ID),
                () -> assertThat(fcmTokenList.get(0).getToken()).isEqualTo(FCM_TOKEN),
                () -> assertThat(fcmTokenList.get(1).getId()).isEqualTo(SECOND_USER_ID),
                () -> assertThat(fcmTokenList.get(1).getToken()).isEqualTo(SECOND_USER_FCM_TOKEN)
        );
    }
}