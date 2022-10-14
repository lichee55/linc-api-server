package egovframework.kit.linc.dto;

import java.util.List;
import java.util.stream.Collectors;

import egovframework.kit.linc.domain.member.Company;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Builder

public class CompanyRequestFormDTO {

    private String companyName;
    private String representativeName;
    private String companyRegistrationNum;
    private String faxNum;
    private String address;
    private String name;
    private String departmentAndPosition;
    private String phoneNum;
    private String email;
    private Integer fullTimeWorker;
    private String sales;
    private String growthDegree;
    private List<Long> businessType;
    private String businessTypeEtc;

    private String mainService;

    public static CompanyRequestFormDTO of(Company company) {

        return CompanyRequestFormDTO.builder().companyName(company.getName()).representativeName(company.getCeoName())
                .companyRegistrationNum(company.getBusinessNumber()).faxNum(company.getFaxNumber())
                .address(company.getHoAddress()).name(company.getOfficerName())
                .departmentAndPosition(company.getOfficerPosition()).phoneNum(company.getPhoneNumber())
                .email(company.getEmail()).fullTimeWorker(company.getNumberEmployees()).sales(company.getYearsSales())
                .businessType(company.getCategoryList().stream().map((e) -> {
                    return e.getBusinessCategoryId().getId();
                }).collect(Collectors.toList())).growthDegree(company.getGrowthDegree().toString())
                .mainService(company.getMajorItem()).businessTypeEtc(company.getCompanyCategoryEtc()).build();
    }
}
