package umc.stockoneqback.business.infra.query;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import umc.stockoneqback.business.domain.Business;
import umc.stockoneqback.business.domain.BusinessRepository;
import umc.stockoneqback.business.dto.response.BusinessList;
import umc.stockoneqback.common.RepositoryTest;
import umc.stockoneqback.field.domain.model.Company;
import umc.stockoneqback.field.domain.model.Store;
import umc.stockoneqback.field.domain.repository.CompanyRepository;
import umc.stockoneqback.field.domain.repository.StoreRepository;
import umc.stockoneqback.global.base.RelationStatus;
import umc.stockoneqback.user.domain.User;
import umc.stockoneqback.user.domain.UserRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static umc.stockoneqback.fixture.StoreFixture.*;
import static umc.stockoneqback.fixture.UserFixture.*;

@DisplayName("Business [Repository Layer] -> BusinessListQueryRepository 테스트")
class BusinessListQueryRepositoryImplTest extends RepositoryTest {
    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private CompanyRepository companyRepository;

    private static Company company;

    private final User[] managerList = new User[3];
    private final User[] supervisorList = new User[3];

    private final Store[] storeList = new Store[3];

    private static final String SEARCH = "위즈";

    @BeforeEach
    void setUp() {
        managerList[0] = userRepository.save(ANNE.toUser());
        managerList[1] = userRepository.save(ELLA.toUser());
        managerList[2] = userRepository.save(MIKE.toUser());

        supervisorList[0] = userRepository.save(WIZ.toUser());
        supervisorList[1] = userRepository.save(JACK.toUser());
        supervisorList[2] = userRepository.save(OLIVIA.toUser());

        storeList[0] = storeRepository.save(Z_YEONGTONG.toStore(managerList[0]));
        storeList[1] = storeRepository.save(Z_SIHEUNG.toStore(managerList[1]));
        storeList[2] = storeRepository.save(Y_YEONGTONG.toStore(managerList[2]));

        managerList[0].registerManagerStore(storeList[0]);
        managerList[1].registerManagerStore(storeList[1]);
        managerList[2].registerManagerStore(storeList[2]);

        company = companyRepository.save(new Company("CafeCompany", "카페", "QWE987"));
        supervisorList[0].registerCompany(company);
        supervisorList[1].registerCompany(company);
        supervisorList[2].registerCompany(company);

        for (int i = 0; i < 3; i++) {
            businessRepository.save(new Business(managerList[i], supervisorList[0]));
            businessRepository.save(new Business(managerList[i], supervisorList[1]));
            businessRepository.save(new Business(managerList[i], supervisorList[2]));
        }
    }

    @Test
    @DisplayName("해당 매니저와 연결된 슈퍼바이저 목록을 조회한다")
    void findSupervisorByManagerIdAndRelationStatus() {
        // given - when
        List<BusinessList> supervisors = businessRepository.findSupervisorByManagerIdAndRelationStatus(managerList[2].getId(), RelationStatus.ACCEPT, SEARCH);

        // then
        assertAll(
                () -> assertThat(supervisors.size()).isEqualTo(1),
                () -> assertThat(supervisors.get(0).getName()).isEqualTo(supervisorList[0].getName())
        );
    }

    @Test
    @DisplayName("해당 슈퍼바이저와 연결된 점주 목록을 조회한다")
    void findManagerBySupervisorIdAndRelationStatus() {
        // given - when
        List<BusinessList> managers = businessRepository.findManagerBySupervisorIdAndRelationStatus(supervisorList[0].getId(), RelationStatus.ACCEPT);

        // then
        assertAll(
                () -> assertThat(managers.size()).isEqualTo(3),
                () -> assertThat(managers.get(0).getName()).isEqualTo(managerList[2].getName()),
                () -> assertThat(managers.get(1).getName()).isEqualTo(managerList[1].getName()),
                () -> assertThat(managers.get(2).getName()).isEqualTo(managerList[0].getName())
        );
    }
}