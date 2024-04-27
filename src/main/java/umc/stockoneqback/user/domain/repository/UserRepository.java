package umc.stockoneqback.user.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import umc.stockoneqback.global.base.Status;
import umc.stockoneqback.user.domain.model.Email;
import umc.stockoneqback.user.domain.model.User;
import umc.stockoneqback.user.infra.query.UserFindQueryRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserFindQueryRepository {
    // @Query
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("UPDATE User u SET u.status = '소멸' WHERE u.id = :userId")
    void expireById(@Param("userId") Long userId);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("DELETE FROM User u WHERE u.modifiedDate <= :overYear and u.status = '소멸'")
    void deleteModifiedOverYearAndExpireUser(@Param("overYear") LocalDate overYear);

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("UPDATE User u SET u.modifiedDate = :modifiedDate WHERE u.id = :userId")
    void updateModifiedDateById(@Param("userId") Long userId, @Param("modifiedDate") LocalDate modifiedDate);

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("UPDATE User u SET u.managerStore.id = :storeId WHERE u.id = :userId")
    void updateManagerStoreIdById(@Param("userId") Long userId, @Param("storeId") Long storeId);

    @Query("SELECT u" +
            " FROM User u" +
            " JOIN FETCH u.roles" +
            " WHERE u.loginId = :loginId")
    Optional<User> findByLoginIdWithRoles(@Param("loginId") String loginId);

    @Query("SELECT u" +
            " FROM User u" +
            " JOIN FETCH u.roles" +
            " WHERE u.id = :userId")
    Optional<User> findByIdWithRoles(@Param("userId") Long userId);

    // Query Method
    boolean existsByLoginIdAndStatus(String loginId, Status status);
    boolean existsByEmailAndStatus(Email email, Status status);
    Optional<User> findByLoginIdAndStatus(String loginId, Status status);
    Optional<User> findByEmailAndStatus(Email email, Status status);
    Optional<User> findByIdAndStatus(Long id, Status status);
}