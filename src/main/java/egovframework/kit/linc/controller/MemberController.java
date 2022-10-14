package egovframework.kit.linc.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import egovframework.kit.linc.domain.member.CompanyQueryConditions;
import egovframework.kit.linc.dto.CompanyDetailDTO;
import org.apache.poi.util.IOUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import egovframework.kit.linc.cmm.ResponseForm;
import egovframework.kit.linc.dto.AddCompanyDTO;
import egovframework.kit.linc.dto.CompanyListDTO;
import egovframework.kit.linc.dto.CompanyRequestFormDTO;
import egovframework.kit.linc.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "로그인", description = "로그인")
    @PostMapping("/api/member/login")
    public ResponseForm<Boolean> login() {

        return new ResponseForm<>(true, "success", 200);
    }

    @Operation(summary = "로그아웃", description = "로그아웃")
    @PostMapping("/api/member/logout")
    public ResponseForm<Boolean> signup() {

        return new ResponseForm<>(true, "success", 200);
    }

    @Operation(summary = "회원가입", description = "회원가입")
    @PostMapping("/api/member/new")
    public ResponseForm<Boolean> signin(@RequestBody AddCompanyDTO dto) {
        try {
            memberService.addCompany(dto);
            return new ResponseForm<>(true, "success", 200);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseForm<>(false, e.getMessage(), 500);
        }
    }

    @Operation(summary = "가입승인 대기 가족회사 조회", description = "가입승인 대기 가족회사 조회")
    @GetMapping("/api/member/pending")
    public ResponseForm<Page<CompanyListDTO>> getPendingCompany(Pageable pageable) {
        try {
            return new ResponseForm<>(memberService.findPendingCompanyList(pageable), "success",
                    200);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseForm<>(null, e.getMessage(), 500);
        }
    }

    @Operation(summary = "애로기술 요청 시 회사정보 조회")
    @GetMapping("/api/member/requestform")
    public ResponseForm<CompanyRequestFormDTO> getCompanyInfoForRequest() {
        try {
            return new ResponseForm<>(memberService.getCompanyInfo(), "success", 200);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseForm<>(null, e.getMessage(), 500);
        }
    }

    @Operation(summary = "가족회사 목록 조회")
    @PostMapping("/api/company")
    public ResponseForm<List<CompanyListDTO>> getCompanyList(@RequestBody CompanyQueryConditions conditions) {
        try {
            List<CompanyListDTO> companyList = memberService.getCompanyList(conditions);
            return new ResponseForm<>(companyList, "success", 200);
        } catch (Exception e) {
            return new ResponseForm<>(null, e.getMessage(), 500);
        }
    }

    @Operation(summary = "가족회사 상세 조회")
    @GetMapping("/api/company")
    public ResponseForm<CompanyDetailDTO> getCompanyDetail(@RequestParam Long companyId) {
        try {
            CompanyDetailDTO companyDetail = memberService.getCompanyDetail(companyId);
            return new ResponseForm<>(companyDetail, "success", 200);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseForm<>(null, e.getMessage(), 500);
        }
    }

    @Operation(summary = "가족기업 엑셀 Export")
    @PostMapping("/api/company/export")
    public void excelExport(HttpServletResponse response, @RequestBody CompanyQueryConditions params) {
        try {
            response.setHeader("Content-Disposition", "attachment;filename=company_export.xlsx");
            response.setContentType("application/octet-stream");

            ByteArrayInputStream stream;
            stream = memberService.companyExport(params);
            IOUtils.copy(stream, response.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}