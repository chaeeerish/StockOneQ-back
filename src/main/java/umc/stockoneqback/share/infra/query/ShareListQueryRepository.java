package umc.stockoneqback.share.infra.query;

import umc.stockoneqback.share.domain.model.Category;
import umc.stockoneqback.share.domain.model.ShareSearchType;
import umc.stockoneqback.share.dto.response.CustomShareListPage;
import umc.stockoneqback.share.infra.query.dto.ShareList;

public interface ShareListQueryRepository{
    CustomShareListPage<ShareList> findShareList(Long businessId, Category category, ShareSearchType shareSearchType, String searchWord, int page);
}
