package egovframework.kit.linc.domain.member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CompanyQueryConditions {

    @Schema(description = "회사명 포함 조건", required = false, example = "회사명")
    private String companyName;

    @Schema(description = "성장 수준 조건(다수 포함 시 or 연산)", required = false, example = "[1, 2, 3]")
    private List<Integer> growthDegree;

    @Schema(description = "회사 타입 조건(다수 포함 시 or 연산)", required = false, example = "[2, 3]")
    private List<String> companyType;

    @Schema(description = "회사 설립일 시작 조건", required = false, example = "2022-09-01")
    private LocalDate startFoundingDate;

    @Schema(description = "회사 설립일 종료 조건", required = false, example = "2022-09-01")
    private LocalDate endFoundingDate;

    @Schema(description = "연매출 시작 조건", required = false, example = "1000")
    private String startYearSales;

    @Schema(description = "연매출 종료 조건", required = false, example = "1000")
    private String endYearSales;

    @Schema(description = "종업원 수 시작 조건", required = false, example = "100")
    private Integer startNumberEmployees;

    @Schema(description = "종업원 수 종료 조건", required = false, example = "100")
    private Integer endNumberEmployees;

    @Schema(description = "업종 조건(다수 포함 시 or 연산)", required = false, example = "[1, 2, 3]")
    private List<Long> categoryList;

}
