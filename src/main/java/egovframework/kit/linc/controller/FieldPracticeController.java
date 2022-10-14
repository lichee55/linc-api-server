package egovframework.kit.linc.controller;

import egovframework.kit.linc.cmm.ResponseForm;
import egovframework.kit.linc.dto.AddFieldPracticeCompanyDTO;
import egovframework.kit.linc.dto.AddFieldPracticeRegisterDTO;
import egovframework.kit.linc.service.FieldPracticeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FieldPracticeController {

    private final FieldPracticeService fieldPracticeService;

    @Operation(summary = "현장실습 회사 등록", description = "현장실습 회사로 등록한다.")
    @PostMapping("/api/field-practice/company")
    public ResponseForm<Boolean> addCompany(@RequestBody AddFieldPracticeCompanyDTO company) {

        try {
            Long aLong = fieldPracticeService.addFieldPracticeCompany(company);
            return new ResponseForm<>(true, "success", 200);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseForm<>(false, "fail", 500);
        }
    }

    @Operation(summary = "현장실습 계획 등록", description = "현장실습 계획을 등록한다.")
    @PostMapping("/api/field-practice/practice-register")
    public ResponseForm<Boolean> addPracticeRegister(@RequestBody AddFieldPracticeRegisterDTO register) {

        try {
            Long aLong = fieldPracticeService.addFieldPracticeRegister(register);
            return new ResponseForm<>(true, "success", 200);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseForm<>(false, "fail", 500);
        }
    }
}
