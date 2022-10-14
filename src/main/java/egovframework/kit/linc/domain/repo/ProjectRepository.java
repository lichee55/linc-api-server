package egovframework.kit.linc.domain.repo;

import egovframework.kit.linc.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long>, CustomProjectRepository {


}
