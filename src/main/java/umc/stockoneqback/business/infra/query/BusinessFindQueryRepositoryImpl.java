package umc.stockoneqback.business.infra.query;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import umc.stockoneqback.business.dto.response.FilteredBusinessUser;
import umc.stockoneqback.business.dto.response.FindBusinessUser;
import umc.stockoneqback.business.dto.response.QFindBusinessUser;
import umc.stockoneqback.field.domain.model.QPartTimer;

import java.util.List;

import static umc.stockoneqback.business.domain.QBusiness.business;
import static umc.stockoneqback.field.domain.model.QStore.store;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BusinessFindQueryRepositoryImpl implements BusinessFindQueryRepository {
    private final JPAQueryFactory query;
    private static final QPartTimer partTimer = new QPartTimer("partTimer");

    @Override
    public FilteredBusinessUser<FindBusinessUser> findBusinessByManager(Long managerId) {
        List<FindBusinessUser> shareLists = query
                .selectDistinct(new QFindBusinessUser(business.id, business.supervisor.id, business.supervisor.name))
                .from(business)
                .where(business.manager.id.eq(managerId))
                .orderBy(business.id.asc())
                .fetch();

        JPAQuery<Long> countQuery = query
                .select(business.countDistinct())
                .from(business)
                .where(business.manager.id.eq(managerId))
                ;

        return new FilteredBusinessUser<>(countQuery.fetchOne(), shareLists);
    }

    @Override
    public FilteredBusinessUser<FindBusinessUser> findBusinessByPartTimer(Long partTimerId) {
        List<FindBusinessUser> shareLists = query
                .selectDistinct(new QFindBusinessUser(business.id, business.supervisor.id, business.supervisor.name))
                .from(business)
                .innerJoin(store).on(store.manager.id.eq(business.manager.id))
                .innerJoin(partTimer).on(partTimer.store.id.eq(store.id))
                .where(partTimer.partTimer.id.eq(partTimerId))
                .orderBy(business.id.asc())
                .fetch();

        JPAQuery<Long> countQuery = query
                .select(business.countDistinct())
                .from(business)
                .innerJoin(store).on(store.manager.id.eq(business.manager.id))
                .innerJoin(partTimer).on(partTimer.store.id.eq(store.id))
                .where(partTimer.partTimer.id.eq(partTimerId))
                ;

        return new FilteredBusinessUser<>(countQuery.fetchOne(), shareLists);
    }

    @Override
    public FilteredBusinessUser<FindBusinessUser> findBusinessBySupervisor(Long supervisorId) {
        List<FindBusinessUser> shareLists = query
                .selectDistinct(new QFindBusinessUser(business.id, business.manager.id, business.manager.name))
                .from(business)
                .where(business.supervisor.id.eq(supervisorId))
                .orderBy(business.id.asc())
                .fetch();

        JPAQuery<Long> countQuery = query
                .select(business.countDistinct())
                .from(business)
                .where(business.supervisor.id.eq(supervisorId))
                ;

        return new FilteredBusinessUser<>(countQuery.fetchOne(), shareLists);
    }
}
