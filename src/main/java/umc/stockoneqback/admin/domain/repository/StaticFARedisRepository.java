package umc.stockoneqback.admin.domain.repository;

import org.springframework.data.repository.CrudRepository;
import umc.stockoneqback.admin.domain.model.StaticFA;

import java.util.List;

public interface StaticFARedisRepository extends CrudRepository<StaticFA, String> {
    List<StaticFA> findAll();
}
