package umc.stockoneqback.auth.controller;

import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import umc.stockoneqback.auth.domain.model.jwt.AuthToken;
import umc.stockoneqback.auth.dto.request.SaveFcmRequest;
import umc.stockoneqback.auth.exception.AuthErrorCode;
import umc.stockoneqback.common.ControllerTest;
import umc.stockoneqback.common.security.mock.WithCustomMockUser;
import umc.stockoneqback.global.exception.BaseException;
import umc.stockoneqback.user.domain.RoleType;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.cookies.CookieDocumentation.cookieWithName;
import static org.springframework.restdocs.cookies.CookieDocumentation.requestCookies;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static umc.stockoneqback.auth.utils.TokenResponseWriter.REFRESH_TOKEN_COOKIE;
import static umc.stockoneqback.fixture.TokenFixture.*;

@DisplayName("Auth [Controller Layer] -> TokenReissueApiController 테스트")
class RefreshTokenReissueApiControllerTest extends ControllerTest {
    @Nested
    @DisplayName("토큰 재발급 API 테스트 [POST /api/token/reissue]")
    class reissueTokens {
        private static final String BASE_URL = "/api/token/reissue";

        @Test
        @DisplayName("Header의 Cookie에 RefreshToken이 없으면 예외가 발생한다")
        void throwExceptionByInvalidPermission() throws Exception {
            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post(BASE_URL)
                    .contentType(APPLICATION_JSON);

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
                                    "TokenReissueApi/Failure/Case1",
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

        @WithCustomMockUser(roleType = RoleType.MANAGER)
        @Test
        @DisplayName("만료된 RefreshToken으로 인해 토큰 재발급에 실패한다")
        void throwExceptionByAuthExpiredToken() throws Exception {
            // given
            doThrow(BaseException.type(AuthErrorCode.AUTH_EXPIRED_TOKEN))
                    .when(jwtTokenProvider)
                    .validateToken(anyString());

            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post(BASE_URL)
                    .contentType(APPLICATION_JSON)
                    .cookie(new Cookie(REFRESH_TOKEN_COOKIE, REFRESH_TOKEN));

            // then
            final AuthErrorCode expectedError = AuthErrorCode.AUTH_EXPIRED_TOKEN;
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
                                    "TokenReissueApi/Failure/Case2",
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

        @WithCustomMockUser(roleType = RoleType.MANAGER)
        @Test
        @DisplayName("이미 사용한 RefreshToken이거나 조작된 RefreshToken이면 재발급에 실패한다")
        void throwExceptionByAuthInvalidToken() throws Exception {
            // given
            doThrow(BaseException.type(AuthErrorCode.AUTH_INVALID_TOKEN))
                    .when(jwtTokenProvider)
                    .validateToken(anyString());

            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post(BASE_URL)
                    .contentType(APPLICATION_JSON)
                    .cookie(new Cookie(REFRESH_TOKEN_COOKIE, REFRESH_TOKEN));

            // then
            final AuthErrorCode expectedError = AuthErrorCode.AUTH_INVALID_TOKEN;
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
                                    "TokenReissueApi/Failure/Case3",
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

        @WithCustomMockUser(roleType = RoleType.MANAGER)
        @Test
        @DisplayName("RefreshToken으로 AccessToken과 RefreshToken을 재발급받는다.")
        void success() throws Exception {
            // given
            given(jwtTokenProvider.getId(REFRESH_TOKEN)).willReturn(1L);

            AuthToken authToken = new AuthToken(ACCESS_TOKEN, REFRESH_TOKEN);
            given(tokenReissueService.invoke(anyString())).willReturn(authToken);

            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post(BASE_URL)
                    .contentType(APPLICATION_JSON)
                    .cookie(new Cookie(REFRESH_TOKEN_COOKIE, REFRESH_TOKEN));

            // then
            mockMvc.perform(requestBuilder)
                    .andExpectAll(
                            status().isNoContent()
                    )
                    .andDo(
                            document(
                                    "TokenReissueApi/Success",
                                    preprocessRequest(prettyPrint()),
                                    preprocessResponse(prettyPrint()),
                                    requestCookies(cookieWithName(REFRESH_TOKEN_COOKIE).description("Refresh Token"))
                            )
                    );
        }
    }
}
