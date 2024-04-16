package umc.stockoneqback.global.security.exception;

import lombok.Getter;
import org.springframework.security.access.AccessDeniedException;
import umc.stockoneqback.global.exception.ErrorCode;

@Getter
public class SecurityJwtAccessDeniedException extends AccessDeniedException {
    private final ErrorCode code;

    private SecurityJwtAccessDeniedException(final ErrorCode code) {
        super(code.getMessage());
        this.code = code;
    }

    public static SecurityJwtAccessDeniedException type(final ErrorCode code) {
        return new SecurityJwtAccessDeniedException(code);
    }
}
