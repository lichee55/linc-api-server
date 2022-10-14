package egovframework.kit.linc.dto;

import egovframework.kit.linc.domain.member.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyListDTO {

    private Long id;
    private String name;
    private String email;
    private String address;
    private String companyNumber;
    private List<Long> categoryList;
    private List<String> categoryTag;
    private Integer numberEmployees;
    private String hoAddress;
    private String ceoName;
    private String companyType;
    private Integer growthDegree;
    private LocalDate foundedDate;
    private String logoImageUrl;

    public CompanyListDTO(
            Long id,
            String name,
            String email,
            String address,
            String companyNumber,
            LocalDate foundedDate,
            String logoImageUrl,
            List<Long> categoryList,
            String companyType,
            Integer growthDegree,
            String categoryTag,
            Integer numberEmployees,
            String hoAddress,
            String ceoName
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.companyNumber = companyNumber;
        this.foundedDate = foundedDate;
        this.logoImageUrl = logoImageUrl;
        this.categoryList = categoryList;
        this.companyType = companyType;
        this.growthDegree = growthDegree;
        this.categoryTag = categoryTag != null ? List.of(categoryTag.split("\\|")) : null;
        this.numberEmployees = numberEmployees;
        this.hoAddress = hoAddress;
        this.ceoName = ceoName;
    }

    public static CompanyListDTO of(Company company) {
        return CompanyListDTO.builder()
                .id(company.getId())
                .name(company.getName())
                .email(company.getEmail())
                .address(company.getHoAddress())
                .companyNumber(company.getBusinessNumber())
                .categoryList(company.getCategoryList().stream().map(c -> c.getBusinessCategoryId().getId()).collect(Collectors.toList()))
                .foundedDate(company.getFoundingDate())
                .logoImageUrl(company.getCompanyLogoImage().getUrl())
                .companyType(company.getCompanyType())
                .growthDegree(company.getGrowthDegree())
                .categoryTag(company.getCategoryTags() != null ? List.of(company.getCategoryTags().split("\\|")) : null)
                .numberEmployees(company.getNumberEmployees())
                .hoAddress(company.getHoAddress())
                .ceoName(company.getCeoName())
                .build();
    }

}
