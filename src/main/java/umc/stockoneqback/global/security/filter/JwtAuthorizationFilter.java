package umc.stockoneqback.global.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import umc.stockoneqback.auth.exception.AuthErrorCode;
import umc.stockoneqback.auth.exception.InvalidTokenException;
import umc.stockoneqback.auth.utils.RequestTokenExtractor;
import umc.stockoneqback.auth.utils.TokenProvider;
import umc.stockoneqback.global.exception.BaseException;
import umc.stockoneqback.global.security.principle.UserPrincipal;
import umc.stockoneqback.user.domain.model.User;
import umc.stockoneqback.user.domain.repository.UserRepository;
import umc.stockoneqback.user.exception.UserErrorCode;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) throws ServletException, IOException {
        final Optional<String> token = RequestTokenExtractor.extractAccessToken(request);

        if (token.isPresent()) {
            try {
                final String accessToken = token.get();
                tokenProvider.validateToken(accessToken);

                final User user = getUserByToken(accessToken);
                applyUserToSecurityContext(user);
            } catch (InvalidTokenException e) {
                throw BaseException.type(AuthErrorCode.AUTH_INVALID_TOKEN);
            }
        }

        filterChain.doFilter(request, response);
    }

    private User getUserByToken(final String accessToken) {
        return userRepository.findByIdWithRoles(tokenProvider.getId(accessToken))
                .orElseThrow(() -> BaseException.type(UserErrorCode.USER_NOT_FOUND));
    }

    private void applyUserToSecurityContext(final User user) {
        final UserPrincipal principal = new UserPrincipal(user);
        final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(principal, "", principal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
