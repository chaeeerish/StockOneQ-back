package umc.stockoneqback.product.infra.query;

import umc.stockoneqback.field.domain.model.Store;
import umc.stockoneqback.product.domain.model.ProductSortCondition;
import umc.stockoneqback.product.domain.model.SearchCondition;
import umc.stockoneqback.product.domain.model.StoreCondition;
import umc.stockoneqback.product.dto.response.ProductFindPage;

import java.util.List;

public interface ProductFindQueryRepository {
    List<ProductFindPage> findProductByName(Store store, StoreCondition storeCondition, String productName);
    List<ProductFindPage> findPageOfSearchConditionOrderBySortCondition(Store store, StoreCondition storeCondition,
                                                                        SearchCondition searchCondition,
                                                                        ProductSortCondition productSortCondition,
                                                                        String productName, Long orderFreq, Integer pageSize);
}
