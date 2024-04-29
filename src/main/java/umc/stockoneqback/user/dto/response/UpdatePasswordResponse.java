package umc.stockoneqback.user.dto.response;

public record UpdatePasswordResponse(
        String loginId,
        Boolean validate
) {
}
