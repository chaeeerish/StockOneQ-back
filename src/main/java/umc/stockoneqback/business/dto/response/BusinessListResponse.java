package umc.stockoneqback.business.dto.response;

import umc.stockoneqback.business.dto.response.BusinessList;

import java.util.List;

public record BusinessListResponse(
        List<BusinessList> userList
) {
}
