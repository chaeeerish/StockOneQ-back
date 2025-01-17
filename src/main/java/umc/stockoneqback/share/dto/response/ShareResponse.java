package umc.stockoneqback.share.dto.response;

import umc.stockoneqback.share.domain.model.Share;

public record ShareResponse(
        Long id,
        String title,
        String file,
        String content,
        Boolean isWriter
) {
    public static ShareResponse toResponse (Share share, Boolean isWriter) {
        return new ShareResponse(share.getId(), share.getTitle(), share.getFile(), share.getContent(), isWriter);
    }
}
