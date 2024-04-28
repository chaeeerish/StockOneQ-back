package umc.stockoneqback.business.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.stockoneqback.auth.domain.model.jwt.Authenticated;
import umc.stockoneqback.business.service.BusinessProductService;
import umc.stockoneqback.global.annotation.Auth;
import umc.stockoneqback.global.base.BaseResponse;
import umc.stockoneqback.product.dto.response.GetTotalProductResponse;
import umc.stockoneqback.product.dto.response.SearchProductOthersResponse;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/business/product")
public class BusinessProductApiController {
    private final BusinessProductService businessProductService;

    @PreAuthorize("hasRole('SUPERVISOR')")
    @GetMapping("/search")
    public BaseResponse<List<SearchProductOthersResponse>> searchProductOthers(@Auth Authenticated authenticated,
                                                                               @RequestParam(value = "manager") Long managerId,
                                                                               @RequestParam(value = "condition") String storeConditionValue,
                                                                               @RequestParam(value = "name") String productName) throws IOException {
        return new BaseResponse<>(businessProductService.searchProductOthers(authenticated.id(), managerId, storeConditionValue, productName));
    }

    @PreAuthorize("hasRole('SUPERVISOR')")
    @GetMapping("/count")
    public BaseResponse<List<GetTotalProductResponse>> getTotalProductOthers(@Auth Authenticated authenticated,
                                                                             @RequestParam(value = "manager") Long managerId,
                                                                             @RequestParam(value = "condition") String storeConditionValue) throws IOException {
        return new BaseResponse<>(businessProductService.getTotalProductOthers(authenticated.id(), managerId, storeConditionValue));
    }

    @PreAuthorize("hasRole('SUPERVISOR')")
    @GetMapping("/page")
    public BaseResponse<List<SearchProductOthersResponse>> getListOfSearchConditionProductOthers(@Auth Authenticated authenticated,
                                                                                                 @RequestParam(value = "manager") Long managerId,
                                                                                                 @RequestParam(value = "condition") String storeConditionValue,
                                                                                                 @RequestParam(value = "search") String searchConditionValue,
                                                                                                 @RequestParam(value = "last", defaultValue = "-1", required = false) Long productId) throws IOException {
        return new BaseResponse<>(businessProductService.getListOfSearchProductOthers(authenticated.id(), managerId, storeConditionValue, productId, searchConditionValue));
    }
}
