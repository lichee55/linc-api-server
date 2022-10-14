package egovframework.kit.linc.service;

import java.time.LocalDateTime;
import java.util.List;

import egovframework.kit.linc.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import egovframework.kit.linc.domain.CollaboRequest;
import egovframework.kit.linc.domain.Status;
import egovframework.kit.linc.domain.TechCareRequest;

public interface CollaboRequestService {

    Boolean setRequestStatus(RequestStatusDTO dto);

    Long addTechCareRequest(AddTechCareRequstDTO techCareRequest);

    Page<RequestListDTO> getRequestList(String type, Status status, LocalDateTime startDate, LocalDateTime endTime,
                                        Pageable pageable);

    CollaboRequest findOneRequest(Long requestId);

    TechCareRequest findOneTechRequest(Long requestId);

}
