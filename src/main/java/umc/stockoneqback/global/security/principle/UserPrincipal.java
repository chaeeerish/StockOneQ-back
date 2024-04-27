package umc.stockoneqback.global.security.principle;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import umc.stockoneqback.user.domain.Role;
import umc.stockoneqback.user.domain.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public record UserPrincipal(
        Long id,
        String name,
        String loginId,
        String password,
        List<String> roles
) implements UserDetails {
    public UserPrincipal(final User user) {
        this(
                user.getId(),
                user.getName(),
                user.getLoginId(),
                user.getPassword().getValue(),
                user.getRoles()
                        .stream()
                        .map(Role::getAuthority)
                        .toList()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final Collection<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return loginId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
