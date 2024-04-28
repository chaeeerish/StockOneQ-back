package umc.stockoneqback.auth.dto.response;

public record LoginResponse(
        Long userId,
        String loginId,
        String name
) {
}
