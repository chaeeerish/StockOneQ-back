package umc.stockoneqback.common.security.mock;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import umc.stockoneqback.global.security.principle.UserPrincipal;

import java.util.List;

public class WithCustomMockUserSecurityContextFactory implements WithSecurityContextFactory<WithCustomMockUser> {

    @Override
    public SecurityContext createSecurityContext(WithCustomMockUser annotation) {
        final UserPrincipal principal = new UserPrincipal(1L, "user_name", "user_login", "user_password", List.of(annotation.roleType().getValue()));
        final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(principal, "", principal.getAuthorities());

        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(authenticationToken);

        return context;
    }
}