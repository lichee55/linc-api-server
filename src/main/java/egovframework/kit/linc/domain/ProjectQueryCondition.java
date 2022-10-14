package egovframework.kit.linc.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectQueryCondition {

    @Schema(description = "현재 상태 조건", required = false, example = "REJECTED, APPROVED, ...")
    private Status status;

    @Schema(description = "업종 포함 조건", required = false, example = "[1, 2, 3]")
    private List<Long> businessTypeIds;

    @Schema(description = "요청 양식 조건", required = false, example = "[Techcare365, 요청서2, 요청서3, ...]")
    private List<String> requestTypeIds;

    @Schema(description = "프로젝트명 포함 조건", required = false, example = "프로젝트명")
    private String projectName;
}
