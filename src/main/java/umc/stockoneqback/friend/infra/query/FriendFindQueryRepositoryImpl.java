package umc.stockoneqback.friend.infra.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import umc.stockoneqback.global.base.RelationStatus;
import umc.stockoneqback.user.dto.FindManager;
import umc.stockoneqback.user.dto.QFindManager;

import java.util.List;

import static umc.stockoneqback.field.domain.model.QStore.store;
import static umc.stockoneqback.friend.domain.model.QFriend.friend;
import static umc.stockoneqback.user.domain.QUser.user;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FriendFindQueryRepositoryImpl implements FriendFindQueryRepository{
    private final JPAQueryFactory query;

    @Override
    public List<FindManager> findReceiversByUserId(Long userId, RelationStatus relationStatus) {
        return query
                .selectDistinct(new QFindManager(friend.receiver.id, friend.receiver.name, friend.receiver.managerStore.name, friend.receiver.phoneNumber))
                .from(friend)
                .innerJoin(user).on(friend.receiver.id.eq(user.id))
                .innerJoin(store).on(friend.receiver.managerStore.id.eq(store.id))
                .where(friend.sender.id.eq(userId), friend.relationStatus.eq(relationStatus))
                .orderBy(friend.receiver.id.asc())
                .fetch();
    }
}
