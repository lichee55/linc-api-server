package egovframework.kit.linc.service.impl;

import egovframework.kit.linc.domain.FieldPracticeCompany;
import egovframework.kit.linc.domain.FieldPracticeRegister;
import egovframework.kit.linc.domain.Files;
import egovframework.kit.linc.domain.repo.CompanyRepository;
import egovframework.kit.linc.domain.repo.FieldPracticeCompanyRepository;
import egovframework.kit.linc.domain.repo.FieldPracticeRegisterRepository;
import egovframework.kit.linc.domain.repo.FilesRepository;
import egovframework.kit.linc.dto.AddFieldPracticeCompanyDTO;
import egovframework.kit.linc.dto.AddFieldPracticeRegisterDTO;
import egovframework.kit.linc.service.FieldPracticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FieldPracticeServiceImpl implements FieldPracticeService {

    private final FieldPracticeCompanyRepository fieldPracticeCompanyRepository;
    private final FieldPracticeRegisterRepository fieldPracticeRegisterRepository;
    private final CompanyRepository companyRepository;
    private final FilesRepository filesRepository;

    @Override
    public Long addFieldPracticeCompany(AddFieldPracticeCompanyDTO addFieldPracticeCompanyDTO) {

        FieldPracticeCompany company = AddFieldPracticeCompanyDTO.toEntity(addFieldPracticeCompanyDTO);
        Files businessLicense = filesRepository.findById(addFieldPracticeCompanyDTO.getBusinessLicense()).orElseThrow(() -> new RuntimeException("잘못된 이미지 정보입니다."));
        company.setBusinessLicense(businessLicense);
        businessLicense.setBusinessLicenseFK(company);
        company.setCompany(companyRepository.findById(addFieldPracticeCompanyDTO.getCompanyId()).orElseThrow(() -> new RuntimeException("잘못된 회사 정보입니다.")));
        fieldPracticeCompanyRepository.save(company);


        return null;
    }

    @Override
    public Long addFieldPracticeRegister(AddFieldPracticeRegisterDTO addFieldPracticeRegisterDTO) {

        FieldPracticeRegister register = AddFieldPracticeRegisterDTO.toEntity(addFieldPracticeRegisterDTO);

        register.setCompany(fieldPracticeCompanyRepository.findById(addFieldPracticeRegisterDTO.getFieldPracticeCompany()).orElseThrow(() -> new RuntimeException("잘못된 현장실습 기업 정보입니다.")));
        fieldPracticeRegisterRepository.save(register);

        return null;
    }
}
