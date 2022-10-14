package egovframework.kit.linc.dto;

import java.util.List;

import egovframework.kit.linc.domain.Status;
import egovframework.kit.linc.domain.TechCareRequest;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class AddTechCareRequstDTO {

	private List<Long> consultantForm;
	private List<String> consultantInfo;
	private List<Long> consultingField;
	private String consultingFieldEtc;
	private String detail;
	private String projectName;
	private String detailInfo;
	private String effectiveness;

	public static TechCareRequest toEntity(AddTechCareRequstDTO dto) {

		return TechCareRequest.builder().wishMentor(String.join("|", dto.getConsultantInfo())).detail(dto.detail)
				.requestName(dto.projectName).cooperationTypeEtc(dto.consultingFieldEtc)
				.expectEffect(dto.getEffectiveness()).status(Status.PENDING).detail(dto.getDetailInfo()).build();

	}
}
