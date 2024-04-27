package umc.stockoneqback.friend.infra.query;

import umc.stockoneqback.global.base.RelationStatus;
import umc.stockoneqback.user.dto.FindManager;

import java.util.List;

public interface FriendFindQueryRepository {
    List<FindManager> findReceiversByUserId(Long userId, RelationStatus relationStatus);
}
