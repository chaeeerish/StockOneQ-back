package umc.stockoneqback.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdatePasswordRequest(
        @NotNull(message = "비밀번호 변경 가능 검증 여부는 필수입니다.")
        Boolean validate,

        @NotBlank(message = "로그인 아이디는 필수입니다.")
        String loginId,

        @NotBlank(message = "새 비밀번호는 필수입니다.")
        String newPassword
) {
}
