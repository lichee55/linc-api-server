package egovframework.kit.linc.dto;

import egovframework.kit.linc.domain.Project;
import egovframework.kit.linc.domain.Status;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectListDTO {

    private Long id;
    private String name;
    private CollaboRequestDTO collaboRequest;
    private Status status;


    public static ProjectListDTO of(Project project) {
        return ProjectListDTO.builder()
                .id(project.getId())
                .name(project.getName())
                .collaboRequest(CollaboRequestDTO.of(project.getCollaboRequest()))
                .status(project.getStatus())
                .build();
    }
}
