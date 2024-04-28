package umc.stockoneqback.user.dto.response;

import umc.stockoneqback.user.dto.FindManager;

import java.util.List;

public record FindManagerResponse (
    List<FindManager> searchedUser
) {
}