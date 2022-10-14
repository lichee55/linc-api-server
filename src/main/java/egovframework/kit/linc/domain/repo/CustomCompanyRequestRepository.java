package egovframework.kit.linc.domain.repo;

import egovframework.kit.linc.domain.member.Company;
import egovframework.kit.linc.domain.member.CompanyQueryConditions;
import egovframework.kit.linc.dto.CompanyDetailDTO;
import egovframework.kit.linc.dto.CompanyListDTO;

import java.util.List;


public interface CustomCompanyRequestRepository {

    List<CompanyDetailDTO> findDynamicQueryDetail(CompanyQueryConditions conditions);

    List<CompanyListDTO> findDynamicQueryList(CompanyQueryConditions conditions);

    Company findCompanyById(Long id);

}
