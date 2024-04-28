package umc.stockoneqback.product.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.stockoneqback.field.domain.model.Store;
import umc.stockoneqback.product.infra.query.ProductFindQueryRepository;
import umc.stockoneqback.user.domain.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductFindQueryRepository {
    @Query("SELECT p FROM Product p WHERE p.status = '정상' AND p.store = :store " +
            "AND p.storeCondition = :storeCondition AND p.name = :name")
    Optional<Product> isExistProductByName(@Param("store") Store store,
                                           @Param("storeCondition") StoreCondition storeCondition,
                                           @Param("name") String productName);

    @Query("SELECT p FROM Product p WHERE p.id = :id AND p.status = '정상'")
    Optional<Product> findProductById(@Param("id") Long productId);

    @Query("SELECT count(p) FROM Product p WHERE p.store = :store " +
            "AND p.storeCondition = :storeCondition AND p.status = '정상'")
    Integer countProductAll(@Param("store") Store store,
                            @Param("storeCondition") StoreCondition storeCondition);

    @Query("SELECT count(p) FROM Product p WHERE p.store = :store AND p.storeCondition = :storeCondition " +
            "AND p.expirationDate < :currentDate AND p.status = '정상'")
    Integer countProductPass(@Param("store") Store store,
                             @Param("storeCondition") StoreCondition storeCondition,
                             @Param("currentDate") LocalDate currentDate);

    @Query("SELECT count(p) FROM Product p WHERE p.store = :store AND p.storeCondition = :storeCondition " +
            "AND (p.expirationDate >= :currentDate AND p.expirationDate <= :standardDate) AND p.status = '정상'")
    Integer countProductClose(@Param("store") Store store,
                              @Param("storeCondition") StoreCondition storeCondition,
                              @Param("currentDate") LocalDate currentDate,
                              @Param("standardDate") LocalDate standardDate);

    @Query("SELECT count(p) FROM Product p WHERE p.store = :store AND p.storeCondition = :storeCondition " +
            "AND p.requireQuant >= p.stockQuant AND p.status = '정상'")
    Integer countProductLack(@Param("store") Store store,
                             @Param("storeCondition") StoreCondition storeCondition);

    @Query("SELECT p FROM Product p WHERE p.status = '정상' AND p.expirationDate < :currentDate AND " +
            "p.store.id = (SELECT s.id FROM Store s WHERE s.manager = :manager) ORDER BY p.name")
    List<Product> findPassByManager(@Param("manager") User user,
                                    @Param("currentDate") LocalDate currentDate);

    @Query("SELECT p FROM Product p WHERE p.status = '정상' AND p.expirationDate < :currentDate AND " +
            "p.store.id = (SELECT t.store.id FROM PartTimer t WHERE t.partTimer = :partTimer) ORDER BY p.name")
    List<Product> findPassByPartTimer(@Param("partTimer") User user,
                                      @Param("currentDate") LocalDate currentDate);
}
