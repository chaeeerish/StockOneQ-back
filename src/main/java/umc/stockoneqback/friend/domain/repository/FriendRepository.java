package umc.stockoneqback.friend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.stockoneqback.friend.domain.model.Friend;
import umc.stockoneqback.friend.infra.query.FriendFindQueryRepository;
import umc.stockoneqback.friend.infra.query.FriendInformationQueryRepository;
import umc.stockoneqback.user.domain.User;

import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, Long>, FriendInformationQueryRepository, FriendFindQueryRepository {
    // @Query
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("DELETE FROM Friend f WHERE f.sender = :user OR f.receiver = :user")
    void deleteFriendByUser(@Param("user") User user);

    // QueryMethod
    Optional<Friend> findBySenderIdAndReceiverId(Long senderId, Long receiverId);
    boolean existsBySenderAndReceiver(User sender, User receiver);
}
