package egovframework.kit.linc.dto;

import java.time.LocalDateTime;

import egovframework.kit.linc.domain.CollaboRequest;
import lombok.Builder;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Builder
@Slf4j
public class PendingRequestListDTO {

    private Long id;
    private String projectName;
    private String projectType;
    private String projectDetail;
    private LocalDateTime createdDate;
    private String companyName;

    public static PendingRequestListDTO of(CollaboRequest request) {

        return PendingRequestListDTO.builder()
                .id(request.getId())
                .projectType(request.getDtype())
                .projectDetail(request.getDetail()).projectName(request.getRequestName())
                .companyName(request.getCompany().getName())
                .createdDate(request.getCreatedAt()).build();
    }
}
