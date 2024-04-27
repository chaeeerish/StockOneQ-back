package umc.stockoneqback.business.service.dto;

import umc.stockoneqback.business.dto.BusinessList;

import java.util.List;

public record BusinessListResponse(
        List<BusinessList> userList
) {
}
