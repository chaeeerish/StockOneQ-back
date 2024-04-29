package umc.stockoneqback.business.infra.query;

import umc.stockoneqback.business.dto.response.FilteredBusinessUser;
import umc.stockoneqback.business.dto.response.FindBusinessUser;

public interface BusinessFindQueryRepository {
    FilteredBusinessUser<FindBusinessUser> findBusinessByManager(Long managerId);
    FilteredBusinessUser<FindBusinessUser> findBusinessByPartTimer(Long partTimerId);
    FilteredBusinessUser<FindBusinessUser> findBusinessBySupervisor(Long supervisorId);
}
