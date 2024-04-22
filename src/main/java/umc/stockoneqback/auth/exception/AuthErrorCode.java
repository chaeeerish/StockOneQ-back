package umc.stockoneqback.auth.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.stockoneqback.global.exception.ErrorCode;

@Getter
@RequiredArgsConstructor
public enum AuthErrorCode implements ErrorCode {
    AUTH_EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH_001", "토큰의 유효기간이 만료되었습니다."),
    AUTH_INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH_002", "토큰이 유효하지 않습니다."),
    INVALID_PERMISSION(HttpStatus.FORBIDDEN, "AUTH_003", "권한이 없습니다."),
    INVALID_AUTH_DATA(HttpStatus.UNAUTHORIZED, "AUTH_004", "아이디나 비밀번호가 일치하지 않습니다."),
    NOT_FOUND_REFRESH_TOKEN(HttpStatus.NOT_FOUND, "AUTH_005", "리프레시 토큰을 찾을 수 없습니다."),
    INVALID_AUTH_CONTENT_TYPE(HttpStatus.BAD_REQUEST, "AUTH_006", "인증방식이 올바르지 않습니다."),
    LOGIN_REQUIRED(HttpStatus.UNAUTHORIZED, "AUTH_007", "로그인이 필요합니다."),
    ;

    private final HttpStatus status;
    private final String errorCode;
    private final String message;
}
