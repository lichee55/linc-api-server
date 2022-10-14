package egovframework.kit.linc.domain.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import egovframework.kit.linc.domain.CollaboRequest;
import egovframework.kit.linc.domain.Status;

public interface CustomCollaboRequestRepository {

	Page<CollaboRequest> findDynamicQuery(String type, Status status, LocalDateTime startDate, LocalDateTime endDate,
			Pageable pageable);

}
