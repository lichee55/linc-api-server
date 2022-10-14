package egovframework.kit.linc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import egovframework.kit.linc.domain.RequestCooperationType;
import egovframework.kit.linc.domain.RequestSupportField;
import egovframework.kit.linc.domain.TechCareRequest;
import egovframework.kit.linc.domain.codetable.CooperationType;
import egovframework.kit.linc.domain.codetable.SupportField;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.stream.Collectors;

@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TechCareRequestDTO extends CollaboRequestDTO {

    private String detail;
    private String cooperationTypeEtc;
    private String wishMentor;
    private String expectEffect;
    private List<Long> supportField;
    private List<Long> cooperationType;


    public static TechCareRequestDTO of(TechCareRequest req) {

        return TechCareRequestDTO.builder()
                .requestId(req.getId())
                .dType(req.getDtype())
                .requestName(req.getRequestName())
                .detail(req.getDetail())
                .status(req.getStatus())
                .createdAt(req.getCreatedAt())
                .updatedAt(req.getUpdatedAt())
                .rejectComment(req.getRejectComment())
                .company(CompanyDetailDTO.of(req.getCompany()))
                .cooperationTypeEtc(req.getCooperationTypeEtc())
                .wishMentor(req.getWishMentor())
                .expectEffect(req.getExpectEffect())
                .supportField(req.getFields().stream().map(RequestSupportField::getSupportField).map(SupportField::getId).collect(Collectors.toList()))
                .cooperationType(req.getTypes().stream().map(RequestCooperationType::getType).map(CooperationType::getId).collect(Collectors.toList()))
                .build();
    }
}
