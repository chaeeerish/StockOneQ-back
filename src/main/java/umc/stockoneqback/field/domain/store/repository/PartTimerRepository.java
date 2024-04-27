package umc.stockoneqback.field.domain.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.stockoneqback.field.domain.store.model.PartTimer;
import umc.stockoneqback.user.domain.model.User;

import java.util.Optional;

public interface PartTimerRepository extends JpaRepository<PartTimer, Long> {
    Optional<PartTimer> findByPartTimer(User user);
    void deleteByPartTimer(User user);
}
