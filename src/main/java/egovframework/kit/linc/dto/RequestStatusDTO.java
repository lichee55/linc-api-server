package egovframework.kit.linc.dto;

import egovframework.kit.linc.domain.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RequestStatusDTO {
    @Schema(description = "요청 ID", example = "1")
    private Long requestId;

    @Schema(description = "요청 상태", example = "APPROVED")
    private Status status;

    @Schema(description = "취소 사유", example = "~이유로 반려되었습니다. 확인 후 다시 신청해주세요.")
    private String comment;
}
