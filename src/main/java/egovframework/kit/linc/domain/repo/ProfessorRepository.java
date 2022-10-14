package egovframework.kit.linc.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import egovframework.kit.linc.domain.member.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

}
