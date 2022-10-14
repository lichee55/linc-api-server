package egovframework.kit.linc.domain.repo.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import egovframework.kit.linc.domain.CompanyBusinessCategory;
import egovframework.kit.linc.domain.QCompanyBusinessCategory;
import egovframework.kit.linc.domain.QFiles;
import egovframework.kit.linc.domain.RequestCooperationField;
import egovframework.kit.linc.domain.codetable.BusinessCategory;
import egovframework.kit.linc.domain.codetable.CooperationField;
import egovframework.kit.linc.domain.member.*;
import egovframework.kit.linc.domain.repo.CustomCompanyRequestRepository;
import egovframework.kit.linc.dto.CompanyDetailDTO;
import egovframework.kit.linc.dto.CompanyListDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CustomCompanyRequestRepositoryImpl implements CustomCompanyRequestRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<CompanyDetailDTO> findDynamicQueryDetail(CompanyQueryConditions conditions) {

        QMember member = QMember.member;
        QCompany company = QCompany.company;
        QCompanyBusinessCategory companyBusinessCategory = QCompanyBusinessCategory.companyBusinessCategory;
        QProfessor professor = QProfessor.professor;


        List<Company> fetch = queryFactory.selectFrom(company)
                .join(member).on(company.id.eq(member.id))
                .leftJoin(company.categoryList, companyBusinessCategory)
                .fetchJoin()
                .leftJoin(companyBusinessCategory.businessCategoryId)
                .fetchJoin()
                .leftJoin(company.mentor_prof, professor)
                .fetchJoin()
                .distinct()
                .where(
                        companyNameEqual(conditions.getCompanyName()),
                        growthDegreeIn(conditions.getGrowthDegree()),
                        companyTypeIn(conditions.getCompanyType()),
                        foundingDateBetween(conditions.getStartFoundingDate(), conditions.getEndFoundingDate()),
                        yearsSalesBetween(conditions.getStartYearSales(), conditions.getEndYearSales()),
                        numberEmployeesEqual(conditions.getStartNumberEmployees(), conditions.getEndNumberEmployees()),
                        categoryListIn(conditions.getCategoryList())
                )
                .fetch();

        List<CompanyDetailDTO> collect = fetch.stream().map(c -> new CompanyDetailDTO(
                c.getName(),
                c.getPhoneNumber(),
                c.getEmail(),
                c.getOfficerName(),
                c.getHoAddress(),
                c.getHoTelephone(),
                c.getFactoryAddress(),
                c.getFactoryTelephone(),
                c.getFaxNumber(),
                c.getCeoName(),
                c.getGrowthDegree(),
                c.getOfficerPosition(),
                c.getCompanyType(),
                c.getBusinessNumber(),
                c.getFoundingDate(),
                c.getCompanyCategoryEtc(),
                c.getCooperationFieldEtc(),
                c.getMajorItem(),
                c.getYearsSales(),
                c.getPossessionTech(),
                c.getNumberEmployees(),
                c.getResearchDept(),
                c.getNumberResearchers(),
                c.getMentor_prof() != null ? c.getMentor_prof().getName() : null,
                c.getSummary(),
                c.getIntroduction(),
                c.getCategoryList().stream().map(CompanyBusinessCategory::getBusinessCategoryId).map(BusinessCategory::getId).collect(Collectors.toList()),
                c.getCategoryTags() != null ? List.of(c.getCategoryTags().split("\\|")) : null,
                c.getCooperationFieldList().stream().map(RequestCooperationField::getCooperationField).map(CooperationField::getId).collect(Collectors.toList())
        )).collect(Collectors.toList());


        return collect;
    }

    @Override
    public List<CompanyListDTO> findDynamicQueryList(CompanyQueryConditions conditions) {
        QMember member = QMember.member;
        QCompany company = QCompany.company;
        QCompanyBusinessCategory companyBusinessCategory = QCompanyBusinessCategory.companyBusinessCategory;
        QFiles files = QFiles.files;

        List<Company> fetch = queryFactory.select(
                        company
                ).from(company)
                .join(member).on(company.id.eq(member.id))
                .leftJoin(company.companyLogoImage, files)
                .fetchJoin()
                .leftJoin(company.categoryList, companyBusinessCategory)
                .fetchJoin()
                .leftJoin(companyBusinessCategory.businessCategoryId)
                .fetchJoin()
                .distinct()
                .where(
                        companyNameEqual(conditions.getCompanyName()),
                        growthDegreeIn(conditions.getGrowthDegree()),
                        companyTypeIn(conditions.getCompanyType()),
                        foundingDateBetween(conditions.getStartFoundingDate(), conditions.getEndFoundingDate()),
                        yearsSalesBetween(conditions.getStartYearSales(), conditions.getEndYearSales()),
                        numberEmployeesEqual(conditions.getStartNumberEmployees(), conditions.getEndNumberEmployees()),
                        categoryListIn(conditions.getCategoryList())
                )
                .fetch();

        List<CompanyListDTO> collect = fetch.stream().map(c -> new CompanyListDTO(
                c.getId(),
                c.getName(),
                c.getEmail(),
                c.getHoAddress(),
                c.getBusinessNumber(),
                c.getFoundingDate(),
                c.getCompanyLogoImage() == null ? null : c.getCompanyLogoImage().getUrl(),
                c.getCategoryList().stream().map(CompanyBusinessCategory::getBusinessCategoryId).map(BusinessCategory::getId).collect(Collectors.toList()),
                c.getCompanyType(),
                c.getGrowthDegree(),
                c.getCategoryTags(),
                c.getNumberEmployees(),
                c.getHoAddress(),
                c.getCeoName()
        )).collect(Collectors.toList());

        return collect;
    }

    @Override
    public Company findCompanyById(Long id) {

        QCompany company = QCompany.company;
        QMember member = QMember.member;
        QCompanyBusinessCategory companyBusinessCategory = QCompanyBusinessCategory.companyBusinessCategory;
        QProfessor professor = QProfessor.professor;
        QFiles files = QFiles.files;

        Company fetch = queryFactory.selectFrom(company)
                .join(member).on(company.id.eq(member.id))
                .leftJoin(company.categoryList, companyBusinessCategory)
                .fetchJoin()
                .leftJoin(companyBusinessCategory.businessCategoryId)
                .fetchJoin()
                .leftJoin(company.mentor_prof, professor)
                .fetchJoin()
                .leftJoin(company.companyLogoImage, files)
                .fetchJoin()
                .distinct()
                .where(
                        company.id.eq(id)
                )
                .fetchFirst();

        return fetch;
    }

    private BooleanExpression companyNameEqual(String companyName) {
        return (companyName != null && !companyName.isEmpty()) ? QCompany.company.name.contains(companyName) : null;
    }

    private BooleanExpression growthDegreeIn(List<Integer> growthDegree) {
        return (growthDegree != null && !growthDegree.isEmpty()) ? QCompany.company.growthDegree.in(growthDegree) : null;
    }

    private BooleanExpression companyTypeIn(List<String> companyType) {
        return (companyType != null && !companyType.isEmpty()) ? QCompany.company.companyType.in(companyType) : null;
    }

    private BooleanExpression foundingDateBetween(LocalDate startDate, LocalDate endDate) {
        return (startDate != null && endDate != null) ? QCompany.company.foundingDate.between(startDate, endDate) : null;
    }

    private BooleanExpression yearsSalesBetween(String startYearsSales, String endYearsSales) {
        return (startYearsSales != null && endYearsSales != null) ? QCompany.company.yearsSales.between(startYearsSales, endYearsSales) : null;
    }

    private BooleanExpression numberEmployeesEqual(Integer startNumberEmployees, Integer endNumberEmployees) {
        return (startNumberEmployees != null && endNumberEmployees != null) ? QCompany.company.numberEmployees.between(startNumberEmployees, endNumberEmployees) : null;
    }

    private BooleanExpression categoryListIn(List<Long> categoryList) {
        return (categoryList != null && !categoryList.isEmpty()) ? QCompanyBusinessCategory.companyBusinessCategory.businessCategoryId.id.in(categoryList) : null;
    }
}
