package egovframework.kit.linc.domain.repo;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import egovframework.kit.linc.domain.Status;
import egovframework.kit.linc.domain.member.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>, CustomCompanyRequestRepository {

    Optional<Company> findById(Long companyId);

    Page<Company> findByStatusOrderByCreatedAtDesc(Status status, Pageable pageable);

}
