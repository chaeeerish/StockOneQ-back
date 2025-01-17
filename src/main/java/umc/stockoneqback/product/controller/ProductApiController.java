package umc.stockoneqback.product.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import umc.stockoneqback.auth.domain.model.jwt.Authenticated;
import umc.stockoneqback.global.annotation.Auth;
import umc.stockoneqback.global.base.BaseResponse;
import umc.stockoneqback.global.exception.GlobalErrorCode;
import umc.stockoneqback.product.dto.request.ProductRequest;
import umc.stockoneqback.product.dto.response.GetRequiredInfoResponse;
import umc.stockoneqback.product.dto.response.LoadProductResponse;
import umc.stockoneqback.product.service.ProductService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductApiController {
    private final ProductService productService;

    @PreAuthorize("hasAnyRole('MANAGER', 'PART_TIMER')")
    @GetMapping("")
    public BaseResponse<GetRequiredInfoResponse> getRequiredInfo(@Auth Authenticated authenticated) {
        return new BaseResponse<>(productService.getRequiredInfo(authenticated.id()));
    }

    @PreAuthorize("hasAnyRole('MANAGER', 'PART_TIMER')")
    @PostMapping("/add")
    public BaseResponse<GlobalErrorCode> saveProduct(@Auth Authenticated authenticated,
                                                     @RequestParam(value = "store") Long storeId,
                                                     @RequestParam(value = "condition") String storeConditionValue,
                                                     @RequestPart(value = "image", required = false) MultipartFile multipartFile,
                                                     @RequestPart(value = "editProductRequest") ProductRequest productRequest) {
        productService.saveProduct(authenticated.id(), storeId, storeConditionValue, productRequest.toProduct(), multipartFile);
        return new BaseResponse<>(GlobalErrorCode.CREATED);
    }

    @PreAuthorize("hasAnyRole('MANAGER', 'PART_TIMER')")
    @GetMapping("/{productId}")
    public BaseResponse<LoadProductResponse> loadProduct(@Auth Authenticated authenticated,
                                                         @PathVariable(value = "productId") Long productId) throws IOException {
        return new BaseResponse<>(productService.loadProduct(authenticated.id(), productId));
    }

    @PreAuthorize("hasAnyRole('MANAGER', 'PART_TIMER')")
    @PatchMapping("/edit/{productId}")
    public BaseResponse<GlobalErrorCode> editProduct(@Auth Authenticated authenticated,
                                                     @PathVariable(value = "productId") Long productId,
                                                     @RequestPart(value = "image", required = false) MultipartFile multipartFile,
                                                     @RequestPart(value = "editProductRequest") ProductRequest productRequest) {
        productService.editProduct(authenticated.id(), productId, productRequest.toProduct(), multipartFile);
        return new BaseResponse<>(GlobalErrorCode.SUCCESS);
    }

    @PreAuthorize("hasAnyRole('MANAGER', 'PART_TIMER')")
    @DeleteMapping("/delete/{productId}")
    public BaseResponse<GlobalErrorCode> deleteProduct(@Auth Authenticated authenticated,
                                                       @PathVariable(value = "productId") Long productId) {
        productService.deleteProduct(authenticated.id(), productId);
        return new BaseResponse<>(GlobalErrorCode.SUCCESS);
    }
}
