package umc.stockoneqback.product.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import umc.stockoneqback.product.domain.model.Product;

import java.time.LocalDate;

public record ProductRequest(
        @NotBlank(message = "제품명은 필수입니다.")
        @Size(max = 11, message = "제품명은 최대 11자까지 입력 가능합니다.")
        String name,

        @NotBlank(message = "가격은 필수입니다.")
        @Size(min = 2, message = "정상적인 가격이 입력되어야 합니다.(10원 이상)")
        Long price,

        @NotBlank(message = "판매업체는 필수입니다.")
        @Size(max = 29, message = "판매업체는 최대 29자까지 입력 가능합니다.")
        String vendor,

        @NotNull(message = "입고일은 필수입니다.")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate receivingDate,

        @NotNull(message = "유통기한은 필수입니다.")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate expirationDate,

        @Size(max = 29, message = "재료위치는 최대 29자까지 입력 가능합니다.")
        String location,

        @NotBlank(message = "필수 수량은 필수입니다.")
        Long requireQuantity,

        @NotBlank(message = "재고 수량은 필수입니다.")
        Long stockQuantity,

        @Size(max = 200, message = "발주사이트는 최대 200자까지 입력 가능합니다.")
        String siteToOrder,

        @NotBlank(message = "발주 빈도는 필수입니다.")
        Long orderFreq
) {
        public Product toProduct() {
                return Product.builder()
                        .name(name)
                        .price(price)
                        .vendor(vendor)
                        .receivingDate(receivingDate)
                        .expirationDate(expirationDate)
                        .location(location)
                        .requireQuant(requireQuantity)
                        .stockQuant(stockQuantity)
                        .siteToOrder(siteToOrder)
                        .orderFreq(orderFreq)
                        .build();
        }
}
