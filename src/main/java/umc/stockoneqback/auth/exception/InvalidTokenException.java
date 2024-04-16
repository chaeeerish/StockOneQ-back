package umc.stockoneqback.auth.exception;

import umc.stockoneqback.global.exception.BaseException;
import umc.stockoneqback.global.exception.ErrorCode;

public class InvalidTokenException extends BaseException {
    private static final ErrorCode code = AuthErrorCode.AUTH_INVALID_TOKEN;

    public InvalidTokenException() {
        super(code);
    }
}
