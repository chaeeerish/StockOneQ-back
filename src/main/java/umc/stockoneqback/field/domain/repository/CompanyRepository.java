package umc.stockoneqback.field.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.stockoneqback.field.domain.model.Company;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByName(String name);
}
