package egovframework.kit.linc.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import egovframework.kit.linc.domain.TechCareRequest;
import egovframework.kit.linc.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import egovframework.kit.linc.cmm.ResponseForm;
import egovframework.kit.linc.domain.CollaboRequest;
import egovframework.kit.linc.domain.Status;
import egovframework.kit.linc.service.CollaboRequestService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CollaboRequestController {

    private final CollaboRequestService requestService;

    @Operation(summary = "애로기술 요청 상태 변경")
    @PostMapping("/api/request/status")
    public ResponseForm<Boolean> setRequestStatus(@RequestBody RequestStatusDTO dto) {
        requestService.setRequestStatus(dto);

        return new ResponseForm<>(true, "success", 200);
    }

    @Operation(summary = "Tech Care 365 요청")
    @PostMapping("/api/request/care365")
    public ResponseForm<Long> addTechCareRequest(@RequestBody AddTechCareRequstDTO techCareRequest) {
        Long requestId = requestService.addTechCareRequest(techCareRequest);

        return new ResponseForm<>(requestId, "success", 200);
    }

    @Operation(summary = "애로기술 목록 조회")
    @GetMapping("/api/request")
    public ResponseForm<Page<RequestListDTO>> getRequests(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            Pageable pageable
    ) {
        try {
            LocalDateTime start = null;
            LocalDateTime end = null;
            if (startDate != null) {
                start = LocalDate.parse(startDate).atStartOfDay();
            }
            if (endDate != null) {
                end = LocalDate.parse(endDate).atTime(23, 59, 59);
            }


            Page<RequestListDTO> list = requestService.getRequestList(type, status, start, end, pageable);
            return new ResponseForm<>(list, "success", 200);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return new ResponseForm<>(null, e.getMessage(), 500);
        }
    }

    @Operation(summary = "애로기술 상세 조회")
    @GetMapping("/api/request/detail")
    public ResponseForm<Object> getReqeust(@RequestParam Long requestId) {
        try {
            CollaboRequest req = requestService.findOneRequest(requestId);
            if (req.getDtype().equals("Techcare365")) {
                TechCareRequestDTO of = TechCareRequestDTO.of((TechCareRequest) req);
                return new ResponseForm<>(of, "success", 200);
            }
            return new ResponseForm<>(null, "success", 200);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return new ResponseForm<>(null, e.getMessage(), 500);
        }
    }
}