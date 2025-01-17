package umc.stockoneqback.friend.infra.query;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import umc.stockoneqback.common.RepositoryTest;
import umc.stockoneqback.field.domain.model.Store;
import umc.stockoneqback.field.domain.repository.StoreRepository;
import umc.stockoneqback.friend.domain.model.Friend;
import umc.stockoneqback.friend.domain.repository.FriendRepository;
import umc.stockoneqback.global.base.RelationStatus;
import umc.stockoneqback.user.domain.User;
import umc.stockoneqback.user.domain.UserRepository;
import umc.stockoneqback.user.dto.FindManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static umc.stockoneqback.fixture.StoreFixture.*;
import static umc.stockoneqback.fixture.UserFixture.*;

@DisplayName("Friend [Repository Layer] -> FriendFindQueryRepository 테스트")
class FriendFindQueryRepositoryImplTest extends RepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private FriendRepository friendRepository;

    private final Store[] storeList = new Store[6];
    private final User[] userList = new User[6];

    @BeforeEach
    void setUp() {
        userList[0] = userRepository.save(ANNE.toUser());
        userList[1] = userRepository.save(ELLA.toUser());
        userList[2] = userRepository.save(MIKE.toUser());
        userList[3] = userRepository.save(SOPHIA.toUser());
        userList[4] = userRepository.save(UNKNOWN.toUser());

        storeList[0] = storeRepository.save(Z_YEONGTONG.toStore(userList[0]));
        storeList[1] = storeRepository.save(Z_SIHEUNG.toStore(userList[1]));
        storeList[2] = storeRepository.save(Y_YEONGTONG.toStore(userList[2]));
        storeList[3] = storeRepository.save(A_PASTA.toStore(userList[3]));
        storeList[4] = storeRepository.save(B_CHICKEN.toStore(userList[4]));
    }

    @Test
    @DisplayName("유저 ID와 Friend 상태로 해당 유저와 친구인 유저의 리스트를 조회한다")
    void findReceiversByUserId() {
        // given
        for (int i = 4; i >= 1; i--) {
            friendRepository.save(Friend.createFriend(userList[0], userList[i], RelationStatus.ACCEPT));
        }

        // when - then
        List<FindManager> findReceivers = friendRepository.findReceiversByUserId(userList[0].getId(), RelationStatus.ACCEPT);

        for (int i = 0; i < findReceivers.size(); i++) {
            FindManager receiver = findReceivers.get(i);
            User user = userList[i + 1];

            assertAll(
                    () -> assertThat(receiver.getId()).isEqualTo(user.getId()),
                    () -> assertThat(receiver.getName()).isEqualTo(user.getName()),
                    () -> assertThat(receiver.getStoreName()).isEqualTo(user.getManagerStore().getName()),
                    () -> assertThat(receiver.getPhoneNumber()).isEqualTo(user.getPhoneNumber())
            );
        }
    }
}