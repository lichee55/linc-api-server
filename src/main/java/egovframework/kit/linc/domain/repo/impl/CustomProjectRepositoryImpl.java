package egovframework.kit.linc.domain.repo.impl;

import com.querydsl.core.group.Group;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import egovframework.kit.linc.domain.*;
import egovframework.kit.linc.domain.codetable.BusinessCategory;
import egovframework.kit.linc.domain.codetable.QBusinessCategory;
import egovframework.kit.linc.domain.codetable.QCooperationField;
import egovframework.kit.linc.domain.member.QCompany;
import egovframework.kit.linc.domain.member.QMember;
import egovframework.kit.linc.domain.repo.CustomProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CustomProjectRepositoryImpl implements CustomProjectRepository {

    private final JPAQueryFactory queryFactory;
    private final QCompanyBusinessCategory qCompanyBusinessCategory = QCompanyBusinessCategory.companyBusinessCategory;
    private final QProject qProject = QProject.project;
    private final QBusinessCategory qBusinessCategory = QBusinessCategory.businessCategory;
    private final QCollaboRequest qCollaboRequest = QCollaboRequest.collaboRequest;
    private final QFiles qFiles = QFiles.files;
    private final QCompany qCompany = QCompany.company;
    private final QRequestCooperationField qRequestCooperationField = QRequestCooperationField.requestCooperationField;

    @Override
    public Page<Project> findDynamicQuery(ProjectQueryCondition condition, Pageable pageable) {

        Map<Long, Group> fetch = queryFactory
                .from(qProject).distinct()
                .join(qProject.collaboRequest, qCollaboRequest)
                .join(qCollaboRequest.company, qCompany)
                .leftJoin(qCompany.companyLogoImage, qFiles)
                .leftJoin(qCompany.categoryList, qCompanyBusinessCategory)
                .leftJoin(qCompany.cooperationFieldList, qRequestCooperationField)
                .where(
                        containsName(condition),
                        eqStatus(condition),
                        inBusinessType(condition),
                        inRequestType(condition)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .transform(
                        groupBy(qProject.id).as(list(qCompanyBusinessCategory), list(qRequestCooperationField))
                );

        long count = fetch.size();

        List<Project> content = queryFactory
                .selectFrom(qProject).distinct()
                .join(qProject.collaboRequest, qCollaboRequest).fetchJoin()
                .join(qCollaboRequest.company, qCompany).fetchJoin()
                .leftJoin(qCompany.companyLogoImage, qFiles).fetchJoin()
                .leftJoin(qCompany.categoryList, qCompanyBusinessCategory).fetchJoin()
                .leftJoin(qCompany.cooperationFieldList, qRequestCooperationField)
                .where(
                        qProject.id.in(fetch.keySet())
                ).fetch();

        return new PageImpl<>(content, pageable, count);
    }

    private BooleanExpression containsName(ProjectQueryCondition condition) {
        if (condition.getProjectName() == null || condition.getProjectName().isEmpty()) {
            return null;
        }
        return QProject.project.name.contains(condition.getProjectName());
    }

    private BooleanExpression eqStatus(ProjectQueryCondition condition) {
        if (condition.getStatus() == null) {
            return null;
        }
        return QProject.project.status.eq(condition.getStatus());
    }

    private BooleanExpression inBusinessType(ProjectQueryCondition condition) {
        if (condition.getBusinessTypeIds() == null || condition.getBusinessTypeIds().isEmpty()) {
            return null;
        }
        List<BusinessCategory> categoryList = queryFactory.selectFrom(qBusinessCategory).where(
                qBusinessCategory.id.in(condition.getBusinessTypeIds())
        ).fetch();

        return QProject.project.collaboRequest.company.id.eq(qCompanyBusinessCategory.companyId.id)
                .and(qCompanyBusinessCategory.businessCategoryId.in(categoryList));
    }

    private BooleanExpression inRequestType(ProjectQueryCondition condition) {
        if (condition.getRequestTypeIds() == null || condition.getRequestTypeIds().isEmpty()) {
            return null;
        }
        return QProject.project.collaboRequest.dtype.in(condition.getRequestTypeIds());
    }

}
