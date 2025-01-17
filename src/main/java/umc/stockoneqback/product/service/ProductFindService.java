package umc.stockoneqback.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.stockoneqback.field.domain.model.Store;
import umc.stockoneqback.field.service.PartTimerService;
import umc.stockoneqback.field.service.StoreService;
import umc.stockoneqback.global.exception.BaseException;
import umc.stockoneqback.global.exception.GlobalErrorCode;
import umc.stockoneqback.product.domain.model.Product;
import umc.stockoneqback.product.domain.model.ProductSortCondition;
import umc.stockoneqback.product.domain.model.SearchCondition;
import umc.stockoneqback.product.domain.model.StoreCondition;
import umc.stockoneqback.product.domain.repository.ProductRepository;
import umc.stockoneqback.product.dto.response.GetTotalProductResponse;
import umc.stockoneqback.product.dto.response.ProductFindPage;
import umc.stockoneqback.product.dto.response.SearchProductResponse;
import umc.stockoneqback.product.exception.ProductErrorCode;
import umc.stockoneqback.user.domain.RoleType;
import umc.stockoneqback.user.domain.User;
import umc.stockoneqback.user.exception.UserErrorCode;
import umc.stockoneqback.user.service.UserFindService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductFindService {
    private final ProductRepository productRepository;
    private final UserFindService userFindService;
    private final StoreService storeService;
    private final ProductService productService;
    private final PartTimerService partTimerService;
    private static final Integer PAGE_SIZE = 12;

    @Transactional
    public List<SearchProductResponse> searchProduct(Long userId, Long storeId, String storeConditionValue,
                                                     String productName) throws IOException {
        Store store = storeService.findById(storeId);
        checkRequestIdHasRequestStore(userId, store);
        StoreCondition storeCondition = StoreCondition.from(storeConditionValue);
        List<ProductFindPage> searchProductUrlList = findProductAllByName(store, storeCondition, productName);
        return convertUrlToResponse(searchProductUrlList);
    }

    @Transactional
    public List<GetTotalProductResponse> getTotalProduct(Long userId, Long storeId, String storeConditionValue) {
        Store store = storeService.findById(storeId);
        productService.checkRequestIdHasRequestStore(userId, store);
        StoreCondition storeCondition = StoreCondition.from(storeConditionValue);
        return countProduct(store, storeCondition);
    }

    @Transactional
    public List<SearchProductResponse> getListOfSearchProduct
            (Long userId, Long storeId, String storeConditionValue, String searchConditionValue, Long productId, String sortConditionValue) throws IOException {
        Product product = configPaging(productId);
        ProductSortCondition productSortCondition = ProductSortCondition.from(sortConditionValue);
        Store store = storeService.findById(storeId);
        productService.checkRequestIdHasRequestStore(userId, store);
        StoreCondition storeCondition = StoreCondition.from(storeConditionValue);
        SearchCondition searchCondition = SearchCondition.findSearchConditionByValue(searchConditionValue);
        List<ProductFindPage> searchProductUrlList = productRepository.findPageOfSearchConditionOrderBySortCondition
                (store, storeCondition, searchCondition, productSortCondition, product.getName(), product.getOrderFreq(), PAGE_SIZE);
        return convertUrlToResponse(searchProductUrlList);
    }

    private Product configPaging(Long productId) {
        if (productId == -1)
            return new Product();
        return productService.findProductById(productId);
    }

    private List<ProductFindPage> findProductAllByName(Store store, StoreCondition storeCondition, String productName) {
        List<ProductFindPage> searchProductUrlList = productRepository.findProductByName(store, storeCondition, productName);
        if (searchProductUrlList.isEmpty())
            throw BaseException.type(ProductErrorCode.NOT_FOUND_PRODUCT);
        return searchProductUrlList;
    }

    private List<GetTotalProductResponse> countProduct(Store store, StoreCondition storeCondition) {
        List<GetTotalProductResponse> countList = new ArrayList<>(4);
        LocalDate currentDate = LocalDate.now();
        LocalDate standardDate = currentDate.plusDays(3);
        countList.add(new GetTotalProductResponse
                ("Total", productRepository.countProductAll(store, storeCondition)));
        countList.add(new GetTotalProductResponse
                ("Pass", productRepository.countProductPass(store, storeCondition, currentDate)));
        countList.add(new GetTotalProductResponse
                ("Close", productRepository.countProductClose(store, storeCondition, currentDate, standardDate)));
        countList.add(new GetTotalProductResponse
                ("Lack", productRepository.countProductLack(store, storeCondition)));
        return countList;
    }

    private List<SearchProductResponse> convertUrlToResponse(List<ProductFindPage> searchProductUrlList) throws IOException {
        List<SearchProductResponse> searchProductResponseList = new ArrayList<>();
        for (ProductFindPage searchProductUrl : searchProductUrlList) {
            byte[] image = productService.getImageOrElseNull(searchProductUrl.getImageUrl());
            SearchProductResponse searchProductResponse = SearchProductResponse.builder()
                    .id(searchProductUrl.getId())
                    .name(searchProductUrl.getName())
                    .image(image)
                    .build();
            searchProductResponseList.add(searchProductResponse);
        }
        return searchProductResponseList;
    }

    public void checkRequestIdHasRequestStore(Long userId, Store store) {
        User user = userFindService.findById(userId);
        if (user.getRoles().get(0).getRoleType() == RoleType.SUPERVISOR)
            throw BaseException.type(GlobalErrorCode.INVALID_USER);
        else if (user.getRoles().get(0).getRoleType() == RoleType.MANAGER) {
            if (storeService.findByUser(user) == store)
                return;
            throw BaseException.type(UserErrorCode.USER_STORE_MATCH_FAIL);
        } else if (user.getRoles().get(0).getRoleType() == RoleType.PART_TIMER) {
            if (partTimerService.findByUser(user).getStore() == store)
                return;
            throw BaseException.type(UserErrorCode.USER_STORE_MATCH_FAIL);
        } else throw BaseException.type(UserErrorCode.ROLE_NOT_FOUND);
    }
}
