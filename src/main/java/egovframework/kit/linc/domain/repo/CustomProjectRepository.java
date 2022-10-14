package egovframework.kit.linc.domain.repo;

import egovframework.kit.linc.domain.Project;
import egovframework.kit.linc.domain.ProjectQueryCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomProjectRepository {

    Page<Project> findDynamicQuery(ProjectQueryCondition condition, Pageable pageable);
}
