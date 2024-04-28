package umc.stockoneqback.friend.dto.response;

import umc.stockoneqback.friend.dto.response.FriendInformation;

import java.util.List;

public record FriendAssembler(
        List<FriendInformation> friends
) {
}
