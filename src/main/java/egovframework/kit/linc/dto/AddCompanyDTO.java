package egovframework.kit.linc.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import egovframework.kit.linc.domain.Status;
import egovframework.kit.linc.domain.member.Company;
import egovframework.kit.linc.domain.member.Relation;
import egovframework.kit.linc.domain.member.Role;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddCompanyDTO {

    @Schema(description = "로그인ID", required = true)
    private String loginId;

    @Schema(description = "비밀번호", required = true)
    private String password;

    @Schema(description = "회사 이름", required = true)
    private String companyName;

    @Schema(description = "대표 이름", required = true)
    private String ceoName;

    @Schema(description = "사업자등록번호", required = true)
    private String businessNumber;

    @Schema(description = "설립일", required = true)
    private LocalDate foundingDate;

    @Schema(description = "업종", required = true)
    private List<Long> companyCategory;

    @Schema(description = "업종 기타", required = false)
    private String companyCategoryEtc;

    @Schema(description = "본사 주소", required = true)
    private String hoAddress;

    @Schema(description = "본사 전화번호", required = true)
    private String hoTelephone;

    @Schema(description = "회사 성장 단계 (1~5)", required = true)
    private Integer growthDegree;

    @Schema(description = "공장 주소", required = false)
    private String factoryAddress;

    @Schema(description = "공장 번호", required = false)
    private String factoryTelephone;

    @Schema(description = "담당자 이름", required = true)
    private String officerName;

    @Schema(description = "담당자 직책", required = true)
    private String officerPosition;

    @Schema(description = "회사 분류 (대기업, 중소기업, 중견기업 ... )", required = true)
    private String companyType;

    @Schema(description = "주요 품목", required = false)
    private String majorItem;

    @Schema(description = "연매출", required = false)
    private String yearsSales;

    @Schema(description = "기업 소유 기술", required = false)
    private String possessionTech;

    @Schema(description = "연구 부서 유/무", required = true)
    private Boolean researchDept;

    @Schema(description = "전업 연구원 수", required = false)
    private Integer numberResearchers;

    @Schema(description = "산학 협력 희망 분야 pk", required = true)
    private List<Long> cooperationField;

    @Schema(description = "산학 협력 희망 분야 기타", required = false)
    private String cooperationFieldEtc;

    @Schema(description = "담당 교수 정보", required = false)
    private ProfessorDTO mentorProfessor;

    @Schema(description = "담당자 휴대폰 번호", required = true)
    private String officerPhoneNumber;

    @Schema(description = "담당자 이메일", required = true)
    private String officerEmail;

    @Schema(description = "촌 수(1촌, 2촌 ...)", required = true)
    private Relation relation;

    @Schema(description = "개요", required = false)
    private String summary;

    @Schema(description = "소개", required = false)
    private String introduction;

    @Schema(description = "종업원 수 ", required = false)
    private Integer numberEmployees;

    @Schema(description = "로고 이미지 PK", required = false)
    private Long logoImage;

    @Schema(description = "소개 이미지 PK들", required = false)
    private List<Long> promotionImage;

    @Schema(description = "회사 태그 (0~3개)", required = false)
    private List<String> companyTag;

    public static Company toEntity(AddCompanyDTO dto) {
        // logoimage, promotionImages 외부에서 추가 필요
        // professor 외부에서 추가 필요
        return Company.builder().name(dto.companyName).loginId(dto.loginId).password(dto.password) // TODO: 암호화 필요.
                .ceoName(dto.ceoName).businessNumber(dto.businessNumber).foundingDate(dto.foundingDate)
                .growthDegree(dto.growthDegree).hoAddress(dto.hoAddress).hoTelephone(dto.hoTelephone)
                .companyCategoryEtc(dto.companyCategoryEtc).cooperationFieldEtc(dto.cooperationFieldEtc)
                .factoryAddress(dto.factoryAddress).factoryTelephone(dto.factoryTelephone).officerName(dto.officerName)
                .officerPosition(dto.officerPosition).companyType(dto.companyType).majorItem(dto.majorItem)
                .yearsSales(dto.yearsSales).possessionTech(dto.possessionTech).researchDept(dto.researchDept)
                .numberResearchers(dto.numberResearchers).phoneNumber(dto.officerPhoneNumber).email(dto.officerEmail)
                .role(Role.ROLE_COMPANY).relation(dto.relation).summary(dto.summary).introduction(dto.introduction)
                .numberEmployees(dto.numberEmployees)
                .categoryTags(String.join("|", dto.companyTag))
                .build();
    }
}
