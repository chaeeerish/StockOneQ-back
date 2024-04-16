package umc.stockoneqback.common;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import umc.stockoneqback.global.config.JpaAuditingConfiguration;
import umc.stockoneqback.global.config.QueryDslConfig;

@DataJpaTest
@Import({QueryDslConfig.class, JpaAuditingConfiguration.class})
@ActiveProfiles("test")
public class RepositoryTest {
}
