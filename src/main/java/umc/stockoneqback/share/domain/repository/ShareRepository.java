package umc.stockoneqback.share.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.stockoneqback.business.domain.model.Business;
import umc.stockoneqback.share.domain.model.Share;
import umc.stockoneqback.share.infra.query.ShareListQueryRepository;

public interface ShareRepository extends JpaRepository<Share, Long>, ShareListQueryRepository {
    void deleteByBusiness(Business business);
}
