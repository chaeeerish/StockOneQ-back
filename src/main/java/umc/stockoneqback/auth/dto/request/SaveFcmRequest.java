package umc.stockoneqback.auth.dto.request;

import jakarta.validation.constraints.NotBlank;

public record SaveFcmRequest(
        @NotBlank(message = "토큰은 필수입니다.")
        String fcmToken
) {
}
