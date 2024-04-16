package umc.stockoneqback.auth.domain.repository;

import org.springframework.data.repository.CrudRepository;
import umc.stockoneqback.auth.domain.model.RefreshToken;

public interface RefreshTokenRedisRepository extends CrudRepository<RefreshToken, Long> {
}
