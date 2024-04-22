package umc.stockoneqback.global.security.exception;

import lombok.Getter;
import org.springframework.security.core.AuthenticationException;
import umc.stockoneqback.global.exception.ErrorCode;

@Getter
public class SecurityJwtAuthenticationException extends AuthenticationException {
    private final ErrorCode code;

    private SecurityJwtAuthenticationException(final ErrorCode code) {
        super(code.getMessage());
        this.code = code;
    }

    public static SecurityJwtAuthenticationException type(final ErrorCode code) {
        return new SecurityJwtAuthenticationException(code);
    }
}
