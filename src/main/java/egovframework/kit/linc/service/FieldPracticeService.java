package egovframework.kit.linc.service;

import egovframework.kit.linc.dto.AddFieldPracticeCompanyDTO;
import egovframework.kit.linc.dto.AddFieldPracticeRegisterDTO;

public interface FieldPracticeService {

    Long addFieldPracticeCompany(AddFieldPracticeCompanyDTO addFieldPracticeCompanyDTO);

    Long addFieldPracticeRegister(AddFieldPracticeRegisterDTO addFieldPracticeRegisterDTO);
}
