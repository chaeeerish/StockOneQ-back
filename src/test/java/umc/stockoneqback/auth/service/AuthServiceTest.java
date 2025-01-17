package umc.stockoneqback.auth.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import umc.stockoneqback.auth.domain.model.fcm.FcmToken;
import umc.stockoneqback.auth.domain.model.jwt.Token;
import umc.stockoneqback.auth.dto.response.AuthMember;
import umc.stockoneqback.auth.exception.AuthErrorCode;
import umc.stockoneqback.auth.service.jwt.AuthService;
import umc.stockoneqback.auth.utils.JwtTokenProvider;
import umc.stockoneqback.common.ServiceTest;
import umc.stockoneqback.field.domain.model.Store;
import umc.stockoneqback.global.exception.BaseException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static umc.stockoneqback.fixture.TokenFixture.FCM_TOKEN;
import static umc.stockoneqback.fixture.UserFixture.SAEWOO;

@DisplayName("Auth [Service Layer] -> AuthService 테스트")
class AuthServiceTest extends ServiceTest {
    @Autowired
    private AuthService authService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private final Long USER_ID = 1L;

    @Nested
    @DisplayName("로그인")
    class login {
        private Long userId;

        @BeforeEach
        void setup() {
            storeRepository.save(createStore("스타벅스 - 광화문점", "카페", "ABC123", "서울시 종로구"));
            userId = userRepository.save(SAEWOO.toUser()).getId();
        }

        @Test
        @DisplayName("비밀번호가 일치하지 않으면 로그인에 실패한다")
        void throwExceptionByWrongPassword() {
            // when - then
            assertThatThrownBy(() -> authService.login(SAEWOO.getLoginId(),  "wrong" + SAEWOO.getPassword()))
                    .isInstanceOf(BaseException.class)
                    .hasMessage(AuthErrorCode.INVALID_AUTH_DATA.getMessage());
        }

        @Test
        @DisplayName("로그인에 성공한다")
        void success() {
            // when
            AuthMember authMember = authService.login(SAEWOO.getLoginId(), SAEWOO.getPassword());

            // then
            Assertions.assertAll(
                    () -> assertThat(authMember).isNotNull(),
                    () -> assertThat(authMember.id()).isEqualTo(userId),
                    () -> assertThat(authMember.loginId()).isEqualTo(SAEWOO.getLoginId()),
                    () -> assertThat(authMember.name()).isEqualTo(SAEWOO.getName()),
                    () -> assertThat(jwtTokenProvider.getId(authMember.accessToken())).isEqualTo(userId),
                    () -> assertThat(jwtTokenProvider.getId(authMember.refreshToken())).isEqualTo(userId),
                    () -> {
                        Token findRefreshToken = tokenRepository.findById(userId).orElseThrow();
                        assertThat(findRefreshToken.getRefreshToken()).isEqualTo(authMember.refreshToken());
                    }
            );
        }
    }

    @Nested
    @DisplayName("로그아웃")
    class logout {
        private Long userId;

        @BeforeEach
        void setup() {
            storeRepository.save(createStore("스타벅스 - 광화문점", "카페", "ABC123", "서울시 종로구"));
            userId = userRepository.save(SAEWOO.toUser()).getId();
        }

        @Test
        @DisplayName("로그아웃에 성공한다")
        void success() {
            // given
            authService.login(SAEWOO.getLoginId(), SAEWOO.getPassword());

            // when
            authService.logout(userId);

            // then
            Optional<Token> findToken = tokenRepository.findById(userId);
            assertThat(findToken).isEmpty();
        }
    }

    @Test
    @DisplayName("사용자의 FCM Token 정보를 저장한다")
    void saveFcmToken() {
        // given
        fcmTokenRedisRepository.save(FcmToken.createFcmToken(USER_ID, FCM_TOKEN));

        // when
        final String newFcmToken = FCM_TOKEN + "_new";
        authService.saveFcm(USER_ID, newFcmToken);

        // then
        FcmToken findToken = fcmTokenRedisRepository.findById(USER_ID).orElseThrow();
        assertThat(findToken.getToken()).isEqualTo(newFcmToken);
    }

    private Store createStore(String name, String sector, String code, String address) {
        return Store.builder()
                .name(name)
                .sector(sector)
                .code(code)
                .address(address)
                .build();
    }
}