package umc.stockoneqback.field.domain.store;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import umc.stockoneqback.field.domain.model.PartTimer;
import umc.stockoneqback.field.domain.model.Store;
import umc.stockoneqback.global.base.Status;
import umc.stockoneqback.user.domain.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static umc.stockoneqback.fixture.StoreFixture.D_PIZZA;
import static umc.stockoneqback.fixture.UserFixture.*;

@DisplayName("Store 도메인 테스트")
class StoreTest {
    @Test
    @DisplayName("Store 생성에 성공한다")
    void createStore() {
        // when
        User manager = ANNE.toUser();
        Store store = Store.createStore(D_PIZZA.getName(), D_PIZZA.getSector(), D_PIZZA.getAddress(), manager);

        // then
        assertAll(
                () -> assertThat(store.getName()).isEqualTo(D_PIZZA.getName()),
                () -> assertThat(store.getSector()).isEqualTo(D_PIZZA.getSector()),
                () -> assertThat(store.getAddress()).isEqualTo(D_PIZZA.getAddress()),
                () -> assertThat(store.getManager()).isEqualTo(manager),
                () -> assertThat(store.getPartTimers().getPartTimers()).isEmpty(),
                () -> assertThat(store.getStatus()).isEqualTo(Status.NORMAL)
        );
    }

    @Test
    @DisplayName("Store Manager 수정에 성공한다")
    void updateStoreManager() {
        // given
        User manager = ANNE.toUser();
        Store store = Store.createStore(D_PIZZA.getName(), D_PIZZA.getSector(), D_PIZZA.getAddress(), manager);

        // when
        User newManager = MIKE.toUser();
        store.updateManager(newManager);

        // then
        assertThat(store.getManager()).isEqualTo(newManager);
    }

    @Test
    @DisplayName("Store PartTimer 등록에 성공한다")
    void updateStorePartTimers() {
        // given
        User manager = ANNE.toUser();
        Store store = Store.createStore(D_PIZZA.getName(), D_PIZZA.getSector(), D_PIZZA.getAddress(), manager);

        // when
        User user1 = SAEWOO.toUser();
        User user2 = BOB.toUser();
        store.updatePartTimer(user1);
        store.updatePartTimer(user2);

        assertAll(
                () -> assertThat(store.getPartTimers().getPartTimers().size()).isEqualTo(2),
                () -> assertThat(store.getPartTimers().getPartTimers().get(0).getPartTimer().getName()).contains(SAEWOO.getName()),
                () -> assertThat(store.getPartTimers().getPartTimers().get(1).getPartTimer().getName()).contains(BOB.getName())
        );
    }

    @Test
    @DisplayName("Store PartTimer 삭제에 성공한다")
    void deleteStorePartTimers() {
        // given
        User manager = ANNE.toUser();
        Store store = Store.createStore(D_PIZZA.getName(), D_PIZZA.getSector(), D_PIZZA.getAddress(), manager);

        User user1 = SAEWOO.toUser();
        User user2 = WONI.toUser();

        PartTimer partTimer1 = PartTimer.createPartTimer(store, user1);
        PartTimer partTimer2 = PartTimer.createPartTimer(store, user2);

        store.updatePartTimer(partTimer1);
        store.updatePartTimer(partTimer2);

        // when
        store.deletePartTimer(partTimer1);

        // then
        assertAll(
                () -> assertThat(store.getPartTimers().getPartTimers().size()).isEqualTo(1),
                () -> assertThat(store.getPartTimers().getPartTimers().get(0).getPartTimer().getName()).contains(WONI.getName())
        );
    }

    @Test
    @DisplayName("Store Manager 삭제에 성공한다")
    void deleteStoreManager() {
        // given
        User manager = ANNE.toUser();
        Store store = Store.createStore(D_PIZZA.getName(), D_PIZZA.getSector(), D_PIZZA.getAddress(), manager);

        // when
        store.deleteManager();

        // then
        assertThat(store.getManager()).isEqualTo(null);
    }
}