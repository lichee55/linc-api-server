package egovframework.kit.linc.dto;

import egovframework.kit.linc.domain.Files;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileDTO {
	private Long id;
	private String name;
	private String type;
	private String url;

	public static FileDTO of(Files file) {
		return FileDTO.builder().id(file.getId()).name(file.getName()).type(file.getType()).url(file.getUrl()).build();
	}
}
