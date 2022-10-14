package egovframework.kit.linc.dto;

import egovframework.kit.linc.domain.FieldPracticeRegister;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class AddFieldPracticeRegisterDTO {

    @Schema(description = "운영과정 (방학과정 : 1, 학기과정 : 2, 방학/학기연계과정 : 3)", required = true, example = "2")
    private Integer course;

    @Schema(description = "실습회사로 등록된 정보의 pk", required = true, example = "1")
    private Long fieldPracticeCompany;

    @Schema(description = "년도", required = true, example = "2021")
    private Integer year;

    @Schema(description = "학기선택 (1학기 :1, 여름학기 :2, 2학기:3, 겨울학기:4)", required = true, example = "3")
    private Integer semester;

    @Schema(description = "운영유형 (직무체험형 : 1, 채용연계형 : 2)", required = true, example = "1")
    private Integer practiceType;

    @Schema(description = "기간", required = true, example = "15")
    private Integer duration;

    @Schema(description = "시작일", required = true, example = "2021-09-01")
    private LocalDate startDate;

    @Schema(description = "종료일", required = true, example = "2021-12-16")
    private LocalDate endDate;

    @Schema(description = "시작시간", required = true, example = "09:00")
    private String startTime;

    @Schema(description = "종료시간", required = true, example = "18:00")
    private String endTime;

    @Schema(description = "실습 요일", required = true, example = "월,화,수,목,금")
    private String dayOfWeek;

    @Schema(description = "연장실습 여부 (연장실습 없음:1, 상황별 실시:2, 주기적/상시적 실시:3)", required = true, example = "2")
    private Integer extendedPractice;

    @Schema(description = "기타사항 (별도 근로 계약 체결 여부 (체결함:1, 체결안함:2)", required = true, example = "2")
    private Integer otherContract;

    @Schema(description = "기타사항 (근로 계약 체결 시 관련사항 또는 기타 특이사항 기입)", required = false, example = "계약 체결 시 기타사항")
    private String otherContractEtc;

    @Schema(description = "기본 수당", required = true, example = "2000000")
    private String payRegular;

    @Schema(description = "연장 수당", required = true, example = "20000")
    private String payExtend;

    @Schema(description = "수당 지급일", required = true, example = "매월 10일")
    private String payDueDate;

    @Schema(description = "기타 지원 사항", required = true, example = "기타 지원 사항")
    private String otherSupport;

    @Schema(description = "전형 방법 (전형 없음:1, 서류전형:2, 면접전형:3, 서류+면접전형:4, 기타:5)", required = true, example = "4")
    private String admissionType;

    @Schema(description = "전형절차 및 일정", required = true, example = "전형절차 및 일정")
    private String admissionProcessDate;

    @Schema(description = "담당자 정보 (분리자 |로 구분)", required = true, example = "업무지원팀|홍길동|과장|010-1234-5678|010-9876-5432|testemail@mail.com")
    private String officerInformation;

    @Schema(description = "실습부서", required = true, example = "실습부서")
    private String practiceDeptName;

    @Schema(description = "실습위치", required = true, example = "구미시 대학로 61")
    private String practiceLocation;

    @Schema(description = "직무명", required = true, example = "백엔드 엔지니어")
    private String practiceName;

    @Schema(description = "교육목표", required = true, example = "교육목표")
    private String practiceGoal;

    @Schema(description = "직무 개요", required = true, example = "직무 개요")
    private String practiceSummary;

    @Schema(description = "운영/지도 계획", required = true, example = "운영/지도 계획")
    private String practicePlan;

    @Schema(description = "학생 요건 정보 (분리자 : |)", required = true, example = "전공분야:컴퓨터공학, 모집인원 0명|3~4학년|3.5/4.5|웹프로그래밍 관련 수업을 수강하였어야 함.|기타사항")
    private String studentRequirement;

    public static FieldPracticeRegister toEntity(AddFieldPracticeRegisterDTO dto) {
        return FieldPracticeRegister.builder()
                .course(dto.getCourse())
                .year(dto.getYear())
                .semester(dto.getSemester())
                .practiceType(dto.getPracticeType())
                .duration(dto.getDuration())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .day(dto.getDayOfWeek())
                .extendedPractice(dto.getExtendedPractice())
                .otherContract(dto.getOtherContract())
                .otherContractEtc(dto.getOtherContractEtc())
                .payRegular(dto.getPayRegular())
                .payExtend(dto.getPayExtend())
                .payDueDate(dto.getPayDueDate())
                .otherSupport(dto.getOtherSupport())
                .admissionType(dto.getAdmissionType())
                .admissionProcessDate(dto.getAdmissionProcessDate())
                .officerInformation(dto.getOfficerInformation())
                .practiceDeptName(dto.getPracticeDeptName())
                .practiceLocation(dto.getPracticeLocation())
                .practiceName(dto.getPracticeName())
                .practiceGoal(dto.getPracticeGoal())
                .practiceSummary(dto.getPracticeSummary())
                .practicePlan(dto.getPracticePlan())
                .studentRequirement(dto.getStudentRequirement())
                .build();
    }

}
