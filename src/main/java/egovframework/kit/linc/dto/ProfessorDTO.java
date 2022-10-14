package egovframework.kit.linc.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProfessorDTO {
	
	public String name;
	public Long id;
	public String dept;
	public String address;
	

}
