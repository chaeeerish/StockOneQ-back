package umc.stockoneqback.global.annotation;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import umc.stockoneqback.auth.domain.model.jwt.TokenType;
import umc.stockoneqback.auth.exception.AuthErrorCode;
import umc.stockoneqback.auth.utils.TokenProvider;
import umc.stockoneqback.global.exception.BaseException;

import static umc.stockoneqback.auth.utils.RequestTokenExtractor.extractAccessToken;
import static umc.stockoneqback.auth.utils.RequestTokenExtractor.extractRefreshToken;

@RequiredArgsConstructor
public class ExtractTokenArgumentResolver implements HandlerMethodArgumentResolver {
    private final TokenProvider tokenProvider;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(ExtractToken.class);
    }

    @Override
    public Object resolveArgument(final MethodParameter parameter,
                                  final ModelAndViewContainer mavContainer,
                                  final NativeWebRequest webRequest,
                                  final WebDataBinderFactory binderFactory) {
        final HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        final ExtractToken extractToken = parameter.getParameterAnnotation(ExtractToken.class);

        final String token = getToken(request, extractToken.tokenType());
        tokenProvider.validateToken(token);
        return token;
    }

    private String getToken(final HttpServletRequest request, final TokenType type) {
        if (type == TokenType.ACCESS) {
            return extractAccessToken(request)
                    .orElseThrow(() -> BaseException.type(AuthErrorCode.INVALID_PERMISSION));
        }
        return extractRefreshToken(request)
                .orElseThrow(() -> BaseException.type(AuthErrorCode.INVALID_PERMISSION));
    }
}
