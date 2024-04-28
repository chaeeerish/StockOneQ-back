package umc.stockoneqback.board.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BoardResponse(
        Long id,
        String title,
        String content,
        int hit,
        int likes,
        LocalDateTime createdDate,
        String writerId,
        String writerName,
        boolean alreadyLike
) {
}
