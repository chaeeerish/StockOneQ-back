package umc.stockoneqback.reply.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ReplyRequest(
        String image,

        @NotBlank(message = "내용 작성은 필수입니다.")
        @Size(min = 1, message = "내용은 최소 1자 이상 작성해주세요.")
        @Size(max = 1000, message = "내용은 1000자 이내로 작성해주세요.")
        String content
) {
}
