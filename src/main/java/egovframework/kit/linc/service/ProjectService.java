package egovframework.kit.linc.service;

import egovframework.kit.linc.domain.ProjectQueryCondition;
import egovframework.kit.linc.dto.ProjectListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjectService {

    Page<ProjectListDTO> findProjects(ProjectQueryCondition condition, Pageable pageable);
}
