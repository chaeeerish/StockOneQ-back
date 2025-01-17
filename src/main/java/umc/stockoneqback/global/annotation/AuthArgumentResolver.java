package umc.stockoneqback.global.annotation;

import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import umc.stockoneqback.auth.domain.model.jwt.Authenticated;
import umc.stockoneqback.auth.exception.AuthErrorCode;
import umc.stockoneqback.global.exception.BaseException;
import umc.stockoneqback.global.security.principle.UserPrincipal;

@RequiredArgsConstructor
public class AuthArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Auth.class);
    }

    @Override
    public Authenticated resolveArgument(final MethodParameter parameter, final ModelAndViewContainer mavContainer,
                                         final NativeWebRequest webRequest, final WebDataBinderFactory binderFactory) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (isNotAuthUser(authentication)) {
            throw BaseException.type(AuthErrorCode.INVALID_PERMISSION);
        }

        return extractAuthInfo(authentication);
    }

    private boolean isNotAuthUser(final Authentication authentication) {
        return !(authentication instanceof UsernamePasswordAuthenticationToken)
                || !(authentication.getPrincipal() instanceof UserPrincipal);
    }

    private Authenticated extractAuthInfo(final Authentication authentication) {
        final UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        return new Authenticated(principal.id(), principal.name(), principal.roles());
    }
}
