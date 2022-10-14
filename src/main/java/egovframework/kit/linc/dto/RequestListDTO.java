package egovframework.kit.linc.dto;

import java.time.LocalDateTime;

import egovframework.kit.linc.domain.CollaboRequest;
import egovframework.kit.linc.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestListDTO {

    private Long id;
    private String projectName;
    private Status status;
    private String projectType;
    private String projectDetail;
    private LocalDateTime createdDate;
    private LocalDateTime updatedAt;
    private CompanyRequestFormDTO companyDetail;

    public static RequestListDTO of(CollaboRequest request) {
        return RequestListDTO.builder()
                .id(request.getId())
                .projectName(request.getRequestName())
                .status(request.getStatus())
                .projectType(request.getDtype())
                .projectDetail(request.getDetail())
                .createdDate(request.getCreatedAt())
                .updatedAt(request.getUpdatedAt())
                .companyDetail(CompanyRequestFormDTO.of(request.getCompany()))
                .build();
    }
}
