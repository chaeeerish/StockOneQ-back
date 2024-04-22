package umc.stockoneqback.auth.domain.repository;

import org.springframework.data.repository.CrudRepository;
import umc.stockoneqback.auth.domain.model.fcm.FcmToken;

import java.util.List;

public interface FcmTokenRedisRepository extends CrudRepository<FcmToken, Long> {
    List<FcmToken> findAll();
}
