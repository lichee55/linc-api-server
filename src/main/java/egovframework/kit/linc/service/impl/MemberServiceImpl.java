package egovframework.kit.linc.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.List;

import egovframework.kit.linc.domain.member.CompanyQueryConditions;
import egovframework.kit.linc.dto.CompanyDetailDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.kit.linc.domain.CompanyBusinessCategory;
import egovframework.kit.linc.domain.Files;
import egovframework.kit.linc.domain.Status;
import egovframework.kit.linc.domain.member.Company;
import egovframework.kit.linc.domain.member.Professor;
import egovframework.kit.linc.domain.repo.BusinessCategoryRepository;
import egovframework.kit.linc.domain.repo.CompanyBusinessCategoryRepository;
import egovframework.kit.linc.domain.repo.CompanyRepository;
import egovframework.kit.linc.domain.repo.FilesRepository;
import egovframework.kit.linc.domain.repo.ProfessorRepository;
import egovframework.kit.linc.dto.AddCompanyDTO;
import egovframework.kit.linc.dto.CompanyListDTO;
import egovframework.kit.linc.dto.CompanyRequestFormDTO;
import egovframework.kit.linc.service.MemberService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

@Slf4j
public class MemberServiceImpl implements MemberService {

    private final CompanyRepository companyRepository;
    private final FilesRepository filesRepository;
    private final ProfessorRepository professorRepository;
    private final CompanyBusinessCategoryRepository comBusinessCategoryRepository;
    private final BusinessCategoryRepository businessCategoryRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Long addCompany(AddCompanyDTO addCompanyDTO) {

        Company company = AddCompanyDTO.toEntity(addCompanyDTO);
        company.setPassword(passwordEncoder.encode(company.getPassword()));

        // ???????????? ????????? ??????
        Long logoId = addCompanyDTO.getLogoImage();
        List<Long> promotionImageIds = addCompanyDTO.getPromotionImage();

        // ?????? ????????? ??????
        if (logoId != null) {
            Files logo = filesRepository.findById(logoId).orElseThrow(() -> new RuntimeException("????????? ????????? ???????????????."));
            company.setLogoImage(logo);
            logo.setLogo(company);
        }

        if (promotionImageIds != null && !promotionImageIds.isEmpty()) {
            List<Files> promotionImages = filesRepository.findAllById(promotionImageIds);
            company.setPromoImage(promotionImages);
            for (Files f : promotionImages) {
                f.setPromotionImagesFK(company);
            }
        }

        // ?????? ?????? PENDING?????? ??????
        company.setPending();

        // ?????? ?????? ??????
        if (addCompanyDTO.getMentorProfessor() != null) {
            Professor mentor = professorRepository.findById(addCompanyDTO.getMentorProfessor().getId())
                    .orElseThrow(() -> new RuntimeException("????????? ???????????????."));
            company.setMentorProfessor(mentor);
        }

        LocalDateTime now = LocalDateTime.now();
        company.setCreatedAt(now);
        company.setUpdatedAt(now);

        Company save = companyRepository.save(company);

        for (Long id : addCompanyDTO.getCompanyCategory()) {

            comBusinessCategoryRepository
                    .save((CompanyBusinessCategory.builder()
                            .businessCategoryId(businessCategoryRepository.findById(id + 1)
                                    .orElseThrow(() -> new RuntimeException("????????? ?????? ID?????????.")))
                            .companyId(save)).build());
        }

        return save.getId();

    }

    @Override
    @Transactional
    public Boolean approveCompany(Long companyId) {

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new IllegalArgumentException("???????????? ?????? ???????????????."));
        company.approve();

        companyRepository.save(company);

        return Boolean.TRUE;
    }

    @Override
    public List<CompanyListDTO> getCompanyList(CompanyQueryConditions conditions) {
        return companyRepository.findDynamicQueryList(conditions);
    }

    @Override
    public Page<CompanyListDTO> findPendingCompanyList(Pageable pageable) {

        return companyRepository.findByStatusOrderByCreatedAtDesc(Status.PENDING, pageable)
                .map(CompanyListDTO::of);
    }

    @Override
    public CompanyRequestFormDTO getCompanyInfo() {
        // TODO ?????? ????????? 1??? ?????? ????????? ?????????. ?????? ?????? ????????? ????????? ?????? ???????????? ????????? ???.

        Company com = companyRepository.findById(1L).orElseThrow(() -> new RuntimeException("?????? ????????? ???????????? ????????????."));

        return CompanyRequestFormDTO.of(com);
    }


    @Override
    public ByteArrayInputStream companyExport(CompanyQueryConditions params) {

        try {

            List<CompanyDetailDTO> dynamicQuery = companyRepository.findDynamicQueryDetail(params);

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet();
            Row row;
            Cell cell;

            int rowNo = 0;

            row = sheet.createRow(rowNo++);
            List<String> header = CompanyDetailDTO.getHeader();
            for (int i = 0; i < header.size(); i++) {
                cell = row.createCell(i);
                cell.setCellValue(header.get(i));
            }

            for (CompanyDetailDTO companyDetailDTO : dynamicQuery) {
                row = sheet.createRow(rowNo++);
                List<String> data = companyDetailDTO.getData();
                for (int i = 0; i < data.size(); i++) {
                    cell = row.createCell(i);
                    cell.setCellValue(data.get(i));
                }
            }
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            workbook.close();
            return new ByteArrayInputStream(outputStream.toByteArray());

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public CompanyDetailDTO getCompanyDetail(Long id) {
        Company company = companyRepository.findCompanyById(id);
        return CompanyDetailDTO.of(company);
    }

}
