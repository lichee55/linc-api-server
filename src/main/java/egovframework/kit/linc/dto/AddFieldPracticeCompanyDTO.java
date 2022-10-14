package egovframework.kit.linc.dto;

import egovframework.kit.linc.domain.DeptForm;
import egovframework.kit.linc.domain.FieldPracticeCompany;
import egovframework.kit.linc.domain.Files;
import egovframework.kit.linc.domain.FormWork;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddFieldPracticeCompanyDTO {

    @Schema(description = "상장여부", required = true, example = "1")
    private Integer isListingStock;

    @Schema(description = "1일기준 근로시간", required = true, example = "8")
    private String timePerDay;

    @Schema(description = "1주기준 근로시간", required = true, example = "40")
    private String timePerWeek;

    @Schema(description = "주 n일", required = true, example = "5")
    private String dayPerWeek;

    @Schema(description = "근로요일", required = true, example = "월,화,수,목,금")
    private String workingDay;

    @Schema(description = "관리부서이름", required = true, example = "기술지원팀")
    private String deptName;

    @Schema(description = "관리부서담당자", required = true, example = "홍길동")
    private String officerName;

    @Schema(description = "관리부서담당자직책", required = true, example = "과장")
    private String officerPosition;

    @Schema(description = "관리부서연락처", required = true, example = "010-1234-5678")
    private String deptContact;

    @Schema(description = "관리부서담당자연락처", required = true, example = "010-1234-5678")
    private String officerPhone;

    @Schema(description = "관리부서이메일", required = true, example = "office@test.com")
    private String email;

    @Schema(description = "관리부서팩스번호", required = true, example = "02-1234-5678")
    private String faxNumber;

    @Schema(description = "check1", required = true, example = "일반사무실 형태, 연구소 형태")
    private String check1;

    @Schema(description = "check2", required = true, example = "일반사무실 형태, 연구소 형태")
    private String check2;

    @Schema(description = "check3", required = true, example = "소속 근로자와 같은 공간에 배정된다.")
    private String check3;

    @Schema(description = "check4", required = true, example = "모든 필요 물품 및 비품을 제공한다.")
    private String check4;

    @Schema(description = "check5", required = true, example = "물품 제조, 생산 등의 활동(업무)에 일부 참여한다.")
    private String check5;

    @Schema(description = "check6", required = true, example = "취급하지 않는다.")
    private String check6;

    @Schema(description = "check6_1", required = true)
    private String check6_1;

    @Schema(description = "check6_2", required = false)
    private String check6_2;

    @Schema(description = "check7", required = true, example = "기타 유의사항을 작성할 수 있습니다.")
    private String check7;

    @Schema(description = "사업자등록증", required = true)
    private Long businessLicense;

    @Schema(description = "기업PK", required = true)
    private Long companyId;

    public static FieldPracticeCompany toEntity(AddFieldPracticeCompanyDTO addFieldPracticeCompanyDTO) {

        return FieldPracticeCompany.builder()
                .isListingStock(addFieldPracticeCompanyDTO.getIsListingStock())
                .formWork(FormWork.builder()
                        .timePerDay(addFieldPracticeCompanyDTO.getTimePerDay())
                        .timePerWeek(addFieldPracticeCompanyDTO.getTimePerWeek())
                        .dayPerWeek(addFieldPracticeCompanyDTO.getDayPerWeek())
                        .workingDay(addFieldPracticeCompanyDTO.getWorkingDay())
                        .build())
                .managementDept(DeptForm.builder()
                        .deptName(addFieldPracticeCompanyDTO.getDeptName())
                        .officerName(addFieldPracticeCompanyDTO.getOfficerName())
                        .officerPosition(addFieldPracticeCompanyDTO.getOfficerPosition())
                        .deptContact(addFieldPracticeCompanyDTO.getDeptContact())
                        .officerPhone(addFieldPracticeCompanyDTO.getOfficerPhone())
                        .email(addFieldPracticeCompanyDTO.getEmail())
                        .faxNumber(addFieldPracticeCompanyDTO.getFaxNumber())
                        .build())
                .check1(addFieldPracticeCompanyDTO.getCheck1())
                .check2(addFieldPracticeCompanyDTO.getCheck2())
                .check3(addFieldPracticeCompanyDTO.getCheck3())
                .check4(addFieldPracticeCompanyDTO.getCheck4())
                .check5(addFieldPracticeCompanyDTO.getCheck5())
                .check6(addFieldPracticeCompanyDTO.getCheck6())
                .check6_1(addFieldPracticeCompanyDTO.getCheck6_1())
                .check6_2(addFieldPracticeCompanyDTO.getCheck6_2())
                .check7(addFieldPracticeCompanyDTO.getCheck7())
                .build();
    }
}
