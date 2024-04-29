package umc.stockoneqback.reply.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ReplyListResponse(
        Long id,
        byte[] image,
        String content,
        LocalDateTime createdDate,
        String writerId,
        String writerName
) {
}
