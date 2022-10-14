package egovframework.kit.linc.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import egovframework.kit.linc.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.kit.linc.domain.CollaboRequest;
import egovframework.kit.linc.domain.RequestCooperationField;
import egovframework.kit.linc.domain.RequestCooperationType;
import egovframework.kit.linc.domain.RequestSupportField;
import egovframework.kit.linc.domain.Status;
import egovframework.kit.linc.domain.TechCareRequest;
import egovframework.kit.linc.domain.codetable.CooperationType;
import egovframework.kit.linc.domain.codetable.SupportField;
import egovframework.kit.linc.domain.repo.CollaboRequestRepository;
import egovframework.kit.linc.domain.repo.CompanyRepository;
import egovframework.kit.linc.domain.repo.CooperationTypeRepository;
import egovframework.kit.linc.domain.repo.RequestCooperationFieldRepository;
import egovframework.kit.linc.domain.repo.RequestCooperationTypeRepository;
import egovframework.kit.linc.domain.repo.RequestSupportFieldRepository;
import egovframework.kit.linc.domain.repo.SupportFieldRepository;
import egovframework.kit.linc.domain.repo.TechCareRequestRepositroy;
import egovframework.kit.linc.service.CollaboRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
@Slf4j
public class CollaboRequestServiceImpl implements CollaboRequestService {

    private final CollaboRequestRepository collabRequestRepository;
    private final TechCareRequestRepositroy techCareRequestRepository;
    private final RequestCooperationTypeRepository reCoopTypeRepo;
    private final RequestSupportFieldRepository reSupportFieldRepo;
    private final CooperationTypeRepository cooperationRepository;
    private final SupportFieldRepository supportFieldRepository;
    private final CompanyRepository companyRepo;

    @Override
    @Transactional
    public Boolean setRequestStatus(RequestStatusDTO dto) {
        CollaboRequest collaboRequest = collabRequestRepository.findById(dto.getRequestId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 요청입니다."));


        collaboRequest.changeStatus(dto.getStatus());
        if (dto.getComment() != null) {
            collaboRequest.setRejectComment(dto.getComment());
        }
        collaboRequest.setUpdatedAt(LocalDateTime.now());
        return true;
    }

    @Override
    @Transactional
    public Long addTechCareRequest(AddTechCareRequstDTO techCareRequest) {

        // TODO 현재 임의로 1번 기업으로 함
        TechCareRequest request = AddTechCareRequstDTO.toEntity(techCareRequest);

        request.setCompany(companyRepo.findById(1L).orElseThrow(() -> new RuntimeException("존재하지 않는 회사입니다.")));
        LocalDateTime now = LocalDateTime.now();
        request.setCreatedAt(now);
        request.setUpdatedAt(now);
        request.setDtype("Techcare365");

        TechCareRequest saved = collabRequestRepository.save(request);

        for (Long id : techCareRequest.getConsultantForm()) {

            reCoopTypeRepo.save(RequestCooperationType.builder().request(saved).type(
                            cooperationRepository.findById(id + 1).orElseThrow(() -> new RuntimeException("잘못된 형태 정보입니다.")))
                    .build());

        }

        for (Long id : techCareRequest.getConsultingField()) {
            reSupportFieldRepo.save(RequestSupportField.builder().request(saved).supportField(
                            supportFieldRepository.findById(id + 1).orElseThrow(() -> new RuntimeException("잘못된 분야 정보입니다.")))
                    .build());
        }

        return saved.getId();
    }

    @Override
    public Page<RequestListDTO> getRequestList(String type, Status status, LocalDateTime startDate,
                                               LocalDateTime endTime, Pageable pageable) {

        Page<CollaboRequest> pages = collabRequestRepository.findDynamicQuery(type, status, startDate, endTime,
                pageable);

        return pages.map(RequestListDTO::of);
    }

    @Override
    public CollaboRequest findOneRequest(Long requestId) {
        return collabRequestRepository.findById(requestId).orElseThrow(() -> new RuntimeException("잘못된 요청 ID입니다."));

    }

    @Override
    public TechCareRequest findOneTechRequest(Long requestId) {
        return techCareRequestRepository.findById(requestId).orElseThrow(() -> new RuntimeException("잘못된 요청 ID입니다."));
    }

}