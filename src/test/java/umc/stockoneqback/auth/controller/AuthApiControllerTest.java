package umc.stockoneqback.auth.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import umc.stockoneqback.auth.dto.request.LoginRequest;
import umc.stockoneqback.auth.dto.request.SaveFcmRequest;
import umc.stockoneqback.auth.dto.response.AuthMember;
import umc.stockoneqback.auth.exception.AuthErrorCode;
import umc.stockoneqback.common.ControllerTest;
import umc.stockoneqback.global.exception.BaseException;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static umc.stockoneqback.fixture.TokenFixture.*;
import static umc.stockoneqback.fixture.UserFixture.SAEWOO;

@DisplayName("Auth [Controller Layer] -> AuthApiController 테스트")
class AuthApiControllerTest extends ControllerTest {
    @Nested
    @DisplayName("로그인 API [POST /api/auth/login]")
    class login {
        private static final String BASE_URL = "/api/auth/login";

        @Test
        @DisplayName("아이디나 비밀번호가 일치하지 않으면 로그인에 실패한다")
        void throwExceptionByInvalidAuthData() throws Exception {
            doThrow(BaseException.type(AuthErrorCode.INVALID_AUTH_DATA))
                    .when(authService)
                    .login(any(), any());

            // when
            final LoginRequest request = createLoginRequest();
            MockHttpServletRequestBuilder requestBuilder = RestDocumentationRequestBuilders
                    .post(BASE_URL)
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request));

            // then
            final AuthErrorCode expectedError = AuthErrorCode.INVALID_AUTH_DATA;
            mockMvc.perform(requestBuilder)
                    .andExpectAll(
                            status().isUnauthorized(),
                            jsonPath("$.status").exists(),
                            jsonPath("$.status").value(expectedError.getStatus().value()),
                            jsonPath("$.errorCode").exists(),
                            jsonPath("$.errorCode").value(expectedError.getErrorCode()),
                            jsonPath("$.message").exists(),
                            jsonPath("$.message").value(expectedError.getMessage())
                    )
                    .andDo(
                            document(
                                    "AuthApi/Login/Failure/Case1",
                                    preprocessRequest(prettyPrint()),
                                    preprocessResponse(prettyPrint()),
                                    requestFields(
                                            fieldWithPath("loginId").description("로그인 아이디"),
                                            fieldWithPath("password").description("비밀번호")
                                    ),
                                    responseFields(
                                            fieldWithPath("status").description("HTTP 상태 코드"),
                                            fieldWithPath("errorCode").description("커스텀 예외 코드"),
                                            fieldWithPath("message").description("예외 메시지")
                                    )
                            )
                    );
        }

        @Test
        @DisplayName("로그인에 성공한다")
        void success() throws Exception {
            AuthMember authMember = createAuthMember();
            given(authService.login(any(), any())).willReturn(authMember);

            // when
            final LoginRequest request = createLoginRequest();
            MockHttpServletRequestBuilder requestBuilder = RestDocumentationRequestBuilders
                    .post(BASE_URL)
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request));

            // then
            mockMvc.perform(requestBuilder)
                    .andExpectAll(
                            status().isOk()
                    )
                    .andDo(
                            document(
                                    "AuthApi/Login/Success",
                                    preprocessRequest(prettyPrint()),
                                    preprocessResponse(prettyPrint()),
                                    requestFields(
                                            fieldWithPath("loginId").description("로그인 아이디"),
                                            fieldWithPath("password").description("비밀번호")
                                    ),
                                    responseFields(
                                            fieldWithPath("id").description("로그인한 사용자 ID (PK)"),
                                            fieldWithPath("loginId").description("로그인 아이디"),
                                            fieldWithPath("name").description("로그인 사용자 이름"),
                                            fieldWithPath("accessToken").description("발급된 Access Token"),
                                            fieldWithPath("refreshToken").description("발급된 Refresh Token")
                                    )
                            )
                    );
        }
    }

    @Nested
    @DisplayName("로그아웃 API [POST /api/auth/logout]")
    class logout {
        private static final String BASE_URL = "/api/auth/logout";
        private static final Long USER_ID = 1L;

        @Test
        @DisplayName("Authorization Header에 AccessToken이 없으면 로그아웃에 실패한다")
        void withoutAccessToken() throws Exception {
            // when
            MockHttpServletRequestBuilder requestBuilder = RestDocumentationRequestBuilders
                    .post(BASE_URL);

            // then
            final AuthErrorCode expectedError = AuthErrorCode.INVALID_PERMISSION;
            mockMvc.perform(requestBuilder)
                    .andExpectAll(
                            status().isForbidden(),
                            jsonPath("$.status").exists(),
                            jsonPath("$.status").value(expectedError.getStatus().value()),
                            jsonPath("$.errorCode").exists(),
                            jsonPath("$.errorCode").value(expectedError.getErrorCode()),
                            jsonPath("$.message").exists(),
                            jsonPath("$.message").value(expectedError.getMessage())
                    )
                    .andDo(
                            document(
                                    "AuthApi/Logout/Failure/Case1",
                                    preprocessRequest(prettyPrint()),
                                    preprocessResponse(prettyPrint()),
                                    responseFields(
                                            fieldWithPath("status").description("HTTP 상태 코드"),
                                            fieldWithPath("errorCode").description("커스텀 예외 코드"),
                                            fieldWithPath("message").description("예외 메시지")
                                    )
                            )
                    );
        }

        @Test
        @DisplayName("로그아웃에 성공한다")
        void success() throws Exception {
            // when
            MockHttpServletRequestBuilder requestBuilder = RestDocumentationRequestBuilders
                    .post(BASE_URL)
                    .header(AUTHORIZATION, BEARER_TOKEN + " " + ACCESS_TOKEN);

            // then
            mockMvc.perform(requestBuilder)
                    .andExpectAll(
                            status().isNoContent()
                    )
                    .andDo(
                            document(
                                    "AuthApi/Logout/Success",
                                    preprocessRequest(prettyPrint()),
                                    preprocessResponse(prettyPrint()),
                                    requestHeaders(
                                            headerWithName(AUTHORIZATION).description("Access Token")
                                    )
                            )
                    );
        }
    }

    @Nested
    @DisplayName("초기 fcmToken 저장 API [POST /api/auth/fcm]")
    class saveFcm {
        private static final String BASE_URL = "/api/auth/fcm";

        @Test
        @DisplayName("초기 fcmToken 저장에 성공한다")
        void success() throws Exception {
            doNothing()
                    .when(authService)
                    .saveFcm(anyLong(), anyString());

            // when
            final SaveFcmRequest request = createSaveFcmRequest();
            MockHttpServletRequestBuilder requestBuilder = RestDocumentationRequestBuilders
                    .post(BASE_URL)
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
                    .header(AUTHORIZATION, BEARER_TOKEN + " " + ACCESS_TOKEN);

            // then
            mockMvc.perform(requestBuilder)
                    .andExpectAll(
                            status().isOk()
                    )
                    .andDo(
                            document(
                                    "AuthApi/SaveFcm/Success",
                                    preprocessRequest(prettyPrint()),
                                    preprocessResponse(prettyPrint()),
                                    requestHeaders(
                                            headerWithName(AUTHORIZATION).description("Access Token")
                                    ),
                                    requestFields(
                                            fieldWithPath("fcmToken").description("현재 사용자의 FCM Token")
                                    )
                            )
                    );
        }
    }

    private LoginRequest createLoginRequest() {
        return new LoginRequest(SAEWOO.getEmail(), SAEWOO.getPassword());
    }

    private AuthMember createAuthMember() {
        return new AuthMember(
                1L,
                SAEWOO.getLoginId(),
                SAEWOO.getName(),
                ACCESS_TOKEN,
                REFRESH_TOKEN);
    }

    private SaveFcmRequest createSaveFcmRequest() {
        return new SaveFcmRequest(FCM_TOKEN);
    }
}