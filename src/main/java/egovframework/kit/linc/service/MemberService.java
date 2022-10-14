package egovframework.kit.linc.service;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;

import egovframework.kit.linc.domain.member.CompanyQueryConditions;
import egovframework.kit.linc.dto.CompanyDetailDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import egovframework.kit.linc.dto.AddCompanyDTO;
import egovframework.kit.linc.dto.CompanyListDTO;
import egovframework.kit.linc.dto.CompanyRequestFormDTO;

public interface MemberService {

    Long addCompany(AddCompanyDTO addCompanyDTO);

    Boolean approveCompany(Long companyId);

    List<CompanyListDTO> getCompanyList(CompanyQueryConditions conditions);

    Page<CompanyListDTO> findPendingCompanyList(Pageable pageable);

    CompanyRequestFormDTO getCompanyInfo();

    ByteArrayInputStream companyExport(CompanyQueryConditions params);

    CompanyDetailDTO getCompanyDetail(Long id);
}
