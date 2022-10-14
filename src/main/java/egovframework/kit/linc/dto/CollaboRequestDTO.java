package egovframework.kit.linc.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import egovframework.kit.linc.domain.CollaboRequest;
import egovframework.kit.linc.domain.RequestCooperationField;
import egovframework.kit.linc.domain.RequestCooperationType;
import egovframework.kit.linc.domain.Status;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class CollaboRequestDTO {

    private Long requestId;
    private Status status;
    private String dType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String rejectComment;
    private String requestName;
    private CompanyDetailDTO company;

    public static CollaboRequestDTO of(CollaboRequest collabRequest) {

        return CollaboRequestDTO.builder()
                .requestId(collabRequest.getId())
                .status(collabRequest.getStatus())
                .requestName(collabRequest.getRequestName())
                .dType(collabRequest.getDtype())
                .createdAt(collabRequest.getCreatedAt())
                .updatedAt(collabRequest.getUpdatedAt())
                .rejectComment(collabRequest.getRejectComment())
                .company(CompanyDetailDTO.of(collabRequest.getCompany()))
                .build();
    }

}
