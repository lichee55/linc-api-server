package egovframework.kit.linc.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import egovframework.kit.linc.domain.CompanyBusinessCategory;
import egovframework.kit.linc.domain.RequestCooperationField;
import egovframework.kit.linc.domain.codetable.BusinessCategory;
import egovframework.kit.linc.domain.codetable.CooperationField;
import egovframework.kit.linc.domain.member.Company;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyDetailDTO {


    public CompanyDetailDTO(
            String companyName,
            String officerPhone,
            String officerEmail,
            String officerName,
            String hoAddress,
            String hoTelephone,
            String factoryAddress,
            String factoryTelephone,
            String faxNumber,
            String ceoName,
            Integer growthDegree,
            String officerPosition,
            String companyType,
            String businessNumber,
            LocalDate foundingDate,
            String companyCategoryEtc,
            String cooperationFieldEtc,
            String majorItem,
            String yearsSales,
            String possessionTech,
            Integer numberEmployees,
            Boolean researchDept,
            Integer numberResearchers,
            String professorName,
            String summary,
            String introduction,
            List<Long> categoryList,
            List<String> categoryTag,
            List<Long> cooperationField
    ) {
        this.companyName = companyName;
        this.officerPhone = officerPhone;
        this.officerEmail = officerEmail;
        this.officerName = officerName;
        this.hoAddress = hoAddress;
        this.hoTelephone = hoTelephone;
        this.factoryAddress = factoryAddress;
        this.factoryTelephone = factoryTelephone;
        this.faxNumber = faxNumber;
        this.ceoName = ceoName;
        this.growthDegree = growthDegree;
        this.officerPosition = officerPosition;
        this.companyType = companyType;
        this.businessNumber = businessNumber;
        this.foundingDate = foundingDate;
        this.companyCategoryEtc = companyCategoryEtc;
        this.cooperationFieldEtc = cooperationFieldEtc;
        this.majorItem = majorItem;
        this.yearsSales = yearsSales;
        this.possessionTech = possessionTech;
        this.numberEmployees = numberEmployees;
        this.researchDept = researchDept;
        this.numberResearchers = numberResearchers;
        this.professorName = professorName;
        this.summary = summary;
        this.introduction = introduction;
        this.categoryList = categoryList;
        this.categoryTag = categoryTag;
        this.cooperationField = cooperationField;
    }

    private String companyName; //
    private String officerPhone;
    private String officerEmail;
    private String officerName;
    private String hoAddress;
    private String hoTelephone;
    private String factoryAddress;
    private String factoryTelephone;
    private String faxNumber;
    private String ceoName;
    private Integer growthDegree; //
    private String officerPosition;
    private String companyType; //
    private String businessNumber;
    private LocalDate foundingDate; //
    private String companyCategoryEtc;
    private String cooperationFieldEtc; //
    private String majorItem;
    private String yearsSales; //
    private String possessionTech;
    private Integer numberEmployees; //
    private Boolean researchDept;
    private Integer numberResearchers;
    private String professorName;
    private String summary;
    private String introduction;
    private List<Long> categoryList; //
    private List<String> categoryTag; //
    private List<Long> cooperationField; //

    public static List<String> getHeader() {
        return List.of(
                "회사명",
                "담당자 연락처",
                "담당자 이메일",
                "담당자 성명",
                "본사 주소",
                "본사 전화번호",
                "공장 주소",
                "공장 전화번호",
                "팩스 번호",
                "대표 성명",
                "기업 성장 수준",
                "담당자 직위",
                "회사 유형",
                "사업자등록번호",
                "설립일",
                "회사 상세 분류 (기타)",
                "협력 분야 (기타)",
                "주력 상품",
                "연 매출",
                "기업 소유 기술",
                "사원 수",
                "연구 부서 여부",
                "전임연구원 수",
                "담당 교수",
                "회사 개요",
                "회사 소개",
                "회사 업종",
                "회사 태그"
        );
    }

    @JsonIgnore
    public List<String> getData() {
        return List.of(
                companyName,
                officerPhone,
                officerEmail,
                officerName,
                hoAddress,
                hoTelephone,
                factoryAddress == null ? "" : factoryAddress,
                factoryTelephone == null ? "" : factoryTelephone,
                faxNumber == null ? "" : faxNumber,
                ceoName,
                growthDegree == null ? "" : growthDegree.toString(),
                officerPosition,
                companyType,
                businessNumber,
                foundingDate == null ? "" : foundingDate.toString(),
                companyCategoryEtc == null ? "" : companyCategoryEtc,
                cooperationFieldEtc == null ? "" : cooperationFieldEtc,
                majorItem == null ? "" : majorItem,
                yearsSales,
                possessionTech == null ? "" : possessionTech,
                numberEmployees == null ? "" : numberEmployees.toString(),
                researchDept == null ? "" : researchDept.toString(),
                numberResearchers == null ? "" : numberResearchers.toString(),
                professorName == null ? "" : professorName.toString(),
                summary == null ? "" : summary,
                introduction == null ? "" : introduction,
                categoryList.stream().map(Object::toString).collect(Collectors.joining(",")),
                categoryTag == null ? "" : String.join(",", categoryTag)
        );
    }

    public static CompanyDetailDTO of(Company com) {
        return CompanyDetailDTO.builder()
                .companyName(com.getName())
                .officerPhone(com.getPhoneNumber())
                .officerEmail(com.getEmail())
                .officerName(com.getOfficerName())
                .hoAddress(com.getHoAddress())
                .hoTelephone(com.getHoTelephone())
                .factoryAddress(com.getFactoryAddress() == null ? null : com.getFactoryAddress())
                .factoryTelephone(com.getFactoryTelephone() == null ? null : com.getFactoryTelephone())
                .faxNumber(com.getFaxNumber() == null ? null : com.getFaxNumber())
                .ceoName(com.getCeoName())
                .growthDegree(com.getGrowthDegree() == null ? null : com.getGrowthDegree())
                .officerPosition(com.getOfficerPosition())
                .companyType(com.getCompanyType())
                .businessNumber(com.getBusinessNumber())
                .foundingDate(com.getFoundingDate() == null ? null : com.getFoundingDate())
                .companyCategoryEtc(com.getCompanyCategoryEtc() == null ? null : com.getCompanyCategoryEtc())
                .cooperationFieldEtc(com.getCooperationFieldEtc() == null ? null : com.getCooperationFieldEtc())
                .majorItem(com.getMajorItem() == null ? null : com.getMajorItem())
                .yearsSales(com.getYearsSales())
                .possessionTech(com.getPossessionTech() == null ? null : com.getPossessionTech())
                .numberEmployees(com.getNumberEmployees() == null ? null : com.getNumberEmployees())
                .researchDept(com.getResearchDept() == null ? null : com.getResearchDept())
                .numberResearchers(com.getNumberResearchers() == null ? null : com.getNumberResearchers())
                .professorName(com.getMentor_prof() == null ? null : com.getMentor_prof().getName())
                .summary(com.getSummary() == null ? null : com.getSummary())
                .introduction(com.getIntroduction() == null ? null : com.getIntroduction())
                .categoryList(com.getCategoryList().stream().map(CompanyBusinessCategory::getBusinessCategoryId).map(BusinessCategory::getId).collect(Collectors.toList()))
                .categoryTag(com.getCategoryTags() == null ? null : List.of(com.getCategoryTags().split("\\|")))
                .cooperationField(com.getCooperationFieldList().stream().map(RequestCooperationField::getCooperationField).map(CooperationField::getId).collect(Collectors.toList()))
                .build();
    }
}
