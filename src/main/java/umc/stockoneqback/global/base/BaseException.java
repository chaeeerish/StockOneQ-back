package umc.stockoneqback.global.base;

public class BaseException extends RuntimeException {
    private final ErrorCode code;

    public BaseException(ErrorCode code) {
        super(code.getMessage());
        this.code = code;
    }

    public static BaseException type(ErrorCode code) {
        return new BaseException(code);
    }
}
