package umc.stockoneqback.global.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.filter.OncePerRequestFilter;
import umc.stockoneqback.global.security.exception.SecurityJwtAccessDeniedException;

import java.io.IOException;

@RequiredArgsConstructor
public class LogoutExceptionTranslationFilter extends OncePerRequestFilter {
    private final AccessDeniedHandler accessDeniedHandler;

    @Override
    protected void doFilterInternal(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (final SecurityJwtAccessDeniedException ex) {
            accessDeniedHandler.handle(request, response, ex);
        }
    }
}
