package egovframework.kit.linc.domain.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import egovframework.kit.linc.domain.CollaboRequest;
import egovframework.kit.linc.domain.Status;

public interface CollaboRequestRepository extends JpaRepository<CollaboRequest, Long>, CustomCollaboRequestRepository {

	List<CollaboRequest> findAllByStatusOrderByCreatedAtDesc(Status status);

	@Query("select c from CollaboRequest c where c.status = :status order by c.createdAt desc")
	List<CollaboRequest> findQuery(Status status);
}
