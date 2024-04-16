package umc.stockoneqback.friend.service.dto;

import lombok.Builder;
import umc.stockoneqback.field.domain.company.Company;

@Builder
public record SearchUserResponse (
        String name,
        String phoneNumber,
        Company company
) {
}
