package umc.stockoneqback.product.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.stockoneqback.global.exception.BaseException;
import umc.stockoneqback.global.utils.EnumStandard;
import umc.stockoneqback.product.exception.ProductErrorCode;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ProductSortCondition implements EnumStandard {
    NAME("가나다"),
    ORDER_FREQUENCY("빈도");

    private final String value;

    public static ProductSortCondition from(String value) {
        return Arrays.stream(values())
                .filter(sortCondition -> sortCondition.value.equals(value))
                .findFirst()
                .orElseThrow(() -> BaseException.type(ProductErrorCode.NOT_FOUND_SORT_CONDITION));
    }
}
