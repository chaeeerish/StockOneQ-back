package umc.stockoneqback.auth.domain.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.stockoneqback.auth.domain.model.jwt.Token;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    // @Query
    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("UPDATE Token t" +
            " SET t.refreshToken = :refreshToken" +
            " WHERE t.userId = :userId")
    void updateRefreshToken(@Param("userId") final Long userId, @Param("refreshToken") final String refreshToken);

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("DELETE FROM Token t WHERE t.userId = :userId")
    void deleteRefreshToken(@Param("userId") final Long userId);

    // Query Method
    Optional<Token> findByUserId(final Long userId);

    boolean existsByUserIdAndRefreshToken(final Long userId, final String refreshToken);
}