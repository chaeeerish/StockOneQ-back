package umc.stockoneqback.friend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.stockoneqback.auth.domain.model.jwt.Authenticated;
import umc.stockoneqback.friend.service.FriendProductService;
import umc.stockoneqback.global.annotation.Auth;
import umc.stockoneqback.global.base.BaseResponse;
import umc.stockoneqback.product.dto.response.GetTotalProductResponse;
import umc.stockoneqback.product.dto.response.SearchProductOthersResponse;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/friend/product")
public class FriendProductApiController {
    private final FriendProductService friendProductService;

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/search")
    public BaseResponse<List<SearchProductOthersResponse>> searchProductOthers(@Auth Authenticated authenticated,
                                                                               @RequestParam(value = "friend") Long friendUser2Id,
                                                                               @RequestParam(value = "condition") String storeConditionValue,
                                                                               @RequestParam(value = "name") String productName) throws IOException {
        return new BaseResponse<>(friendProductService.searchProductOthers(authenticated.id(), friendUser2Id, storeConditionValue, productName));
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/count")
    public BaseResponse<List<GetTotalProductResponse>> getTotalProductOthers(@Auth Authenticated authenticated,
                                                                             @RequestParam(value = "friend") Long friendUser2Id,
                                                                             @RequestParam(value = "condition") String storeConditionValue) throws IOException {
        return new BaseResponse<>(friendProductService.getTotalProductOthers(authenticated.id(), friendUser2Id, storeConditionValue));
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/page")
    public BaseResponse<List<SearchProductOthersResponse>> getListOfSearchConditionProductOthers(@Auth Authenticated authenticated,
                                                                                                 @RequestParam(value = "friend") Long friendUser2Id,
                                                                                                 @RequestParam(value = "condition") String storeConditionValue,
                                                                                                 @RequestParam(value = "search") String searchConditionValue,
                                                                                                 @RequestParam(value = "last", defaultValue = "-1", required = false) Long productId) throws IOException {
        return new BaseResponse<>(friendProductService.getListOfSearchProductOthers(authenticated.id(), friendUser2Id, storeConditionValue, productId, searchConditionValue));
    }
}
