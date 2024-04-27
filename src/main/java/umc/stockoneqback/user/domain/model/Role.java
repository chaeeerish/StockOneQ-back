package umc.stockoneqback.user.domain.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    private Role(final User user, final RoleType roleType) {
        this.user = user;
        this.roleType = roleType;
    }

    public static Role createRole(final User user, final RoleType roleType) {
        return new Role(user, roleType);
    }

    public String getAuthority() {
        return roleType.getAuthority();
    }
}
