package egovframework.kit.linc.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Getter;

@Entity
@Table
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class FieldPracticeRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "field_practice_company_id")
    private FieldPracticeCompany company;

    // 운영과정 (방학과정 : 1, 학기과정 : 2, 방학/학기연계과정 : 3)
    @Column(name = "course")
    private Integer course;

    @Column(name = "course_year")
    private Integer year;

    // 학기선택 (1학기 :1, 여름학기 :2, 2학기:3, 겨울학기:4)
    @Column(name = "course_semester")
    private Integer semester;

    // 운영유형 (직무체험형 : 1, 채용연계형 : 2)
    @Column(name = "practice_type")
    private Integer practiceType;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    // 실습 요일
    @Column(name = "work_days")
    private String day;

    // 연장실습 여부 (연장실습 없음:1, 상황별 실시:2, 주기적/상시적 실시:3)
    @Column(name = "extended_practice")
    private Integer extendedPractice;

    // 기타사항 (별도 근로 계약 체결 여부 (체결함:1, 체결안함:2)
    @Column(name = "other_contract")
    private Integer otherContract;

    // 근로 계약 체결 시 관련사항 또는 기타 특이사항 기입
    @Column(name = "other_contract_etc")
    private String otherContractEtc;

    @Column(name = "pay_regular")
    private String payRegular;

    @Column(name = "pay_extend")
    private String payExtend;

    @Column(name = "pay_due_date")
    private String payDueDate;

    // 기타 지원 사항
    @Column(name = "other_support")
    private String otherSupport;

    // 전형 방법
    @Column(name = "admission_type")
    private String admissionType;

    // 전형절차 및 일정
    @Column(name = "admission_process_date")
    private String admissionProcessDate;

    // 담당자 정보 (분리자 |로 구분)
    @Column(name = "officer_information")
    private String officerInformation;

    // 실습직무
    @Column(name = "practice_dept_name")
    private String practiceDeptName;

    @Column(name = "practice_location")
    private String practiceLocation;

    // 직무명
    @Column(name = "practice_name")
    private String practiceName;

    // 교육목표
    @Column(name = "practice_goal")
    private String practiceGoal;

    // 직무개요
    @Column(name = "practice_summary")
    private String practiceSummary;

    // 운영/지도 계획
    @Column(name = "practice_plan")
    private String practicePlan;

    // 학생 요건 정보 (분리자 : |)
    @Column(name = "student_requirement")
    private String studentRequirement;

    public void setCompany(FieldPracticeCompany company) {
        this.company = company;
    }
}
