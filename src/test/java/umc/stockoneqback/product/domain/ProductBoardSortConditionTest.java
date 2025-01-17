package umc.stockoneqback.product.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import umc.stockoneqback.global.exception.BaseException;
import umc.stockoneqback.product.domain.model.ProductSortCondition;
import umc.stockoneqback.product.exception.ProductErrorCode;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("SortCondition 도메인 테스트")
class ProductBoardSortConditionTest {
    @Test
    @DisplayName("존재하지 않는 정렬조건이 입력되면 예외가 발생한다")
    void throwExceptionByWrongSortCondition() {
        assertThatThrownBy(() -> ProductSortCondition.from(ProductSortCondition.NAME.getValue() + "거짓"))
                .isInstanceOf(BaseException.class)
                .hasMessage(ProductErrorCode.NOT_FOUND_SORT_CONDITION.getMessage());
    }
}