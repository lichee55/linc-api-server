package egovframework.kit.linc.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import egovframework.kit.linc.domain.codetable.BusinessCategory;

public interface BusinessCategoryRepository extends JpaRepository<BusinessCategory, Long> {

}
