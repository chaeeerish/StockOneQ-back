package umc.stockoneqback.user.dto.response;

import lombok.Builder;

@Builder
public record GetFAResponse(
        String question,
        String answer
) {
}
