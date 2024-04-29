package umc.stockoneqback.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import umc.stockoneqback.global.utils.EnumConverter;
import umc.stockoneqback.global.utils.EnumStandard;

@Getter
@RequiredArgsConstructor
public enum RoleType implements EnumStandard {
    MANAGER("ROLE_MANAGER", "사장님"),
    PART_TIMER("ROLE_PART_TIMER", "아르바이트생"),
    SUPERVISOR("ROLE_SUPERVISOR", "슈퍼바이저"),
    ADMIN("ROLE_ADMIN", "관리자"),
    ;

    private final String authority;
    private final String description;

    @Override
    public String getValue() {
        return authority;
    }

    @jakarta.persistence.Converter
    public static class RoleTypeConverter extends EnumConverter<RoleType> {
        public RoleTypeConverter() {
            super(RoleType.class);
        }
    }
}
