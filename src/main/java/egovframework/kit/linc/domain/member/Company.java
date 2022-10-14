package egovframework.kit.linc.domain.member;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import egovframework.kit.linc.domain.CompanyBusinessCategory;
import egovframework.kit.linc.domain.Files;
import egovframework.kit.linc.domain.RequestCooperationField;
import egovframework.kit.linc.domain.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Table(name = "companies")
@Entity
@DiscriminatorValue("C")
@SuperBuilder
@NoArgsConstructor
@Getter
@ToString
public class Company extends Member {

    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updatedAt", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "deletedAt")
    private LocalDateTime deletedAt;

    @Column(name = "HO_address", nullable = false)
    private String hoAddress;

    @Column(name = "HO_telephone", nullable = false)
    private String hoTelephone;

    @Column(name = "factory_address")
    private String factoryAddress;

    @Column(name = "factory_telephone")
    private String factoryTelephone;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "fax_number")
    private String faxNumber;

    // 대표 이름
    @Column(name = "ceo_name", nullable = false)
    private String ceoName;

    // 회사 규모
    @Column(name = "growth_degree")
    private Integer growthDegree;

    // 담당자 이름
    @Column(name = "officer_name", nullable = false)
    private String officerName;

    // 담당자 직책
    @Column(name = "officer_position", nullable = false)
    private String officerPosition;

    // 기업형태 (대기업, 중견기업, 중소기업, 벤처기업, 연구소기업, 기타)
    @Column(name = "company_type", nullable = false)
    private String companyType;

    @Column(name = "business_number", nullable = false)
    private String businessNumber;

    @Column(name = "founding_date", nullable = false)
    private LocalDate foundingDate;

    @Column(name = "company_category_etc")
    private String companyCategoryEtc;

    @Column(name = "cooperation_field_etc")
    private String cooperationFieldEtc;

    @Column(name = "category_tags")
    private String categoryTags;

    // 주요 품목
    @Column(name = "major_item")
    private String majorItem;

    // 전년도 매출액
    @Column(name = "years_sales", nullable = false)
    private String yearsSales;

    // 보유기술
    @Column(name = "possession_tech")
    private String possessionTech;

    // 인력 현황(상시 종업원 수)
    @Column(name = "number_employees", nullable = false)
    private Integer numberEmployees;

    @Column(name = "research_dept_existence", nullable = false)
    private Boolean researchDept;

    @Column(name = "number_researchers")
    private Integer numberResearchers;

    // 멘토교수
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_professor")
    private Professor mentor_prof;

    @Enumerated(EnumType.STRING)
    @Column(name = "relation", nullable = false)
    private Relation relation;

    @Column(name = "summary", nullable = false)
    private String summary;

    @Column(name = "introduction", nullable = false)
    private String introduction;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "companyLogoImageFK")
    private Files companyLogoImage;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "promotionImagesFK")
    private List<Files> promotionImages;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "companyId")
    private List<CompanyBusinessCategory> categoryList;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "companyId")
    private List<RequestCooperationField> cooperationFieldList;

    public void setLogoImage(Files build) {
        this.companyLogoImage = build;
    }

    public void setPending() {
        this.status = Status.PENDING;
    }

    public void approve() {
        this.status = Status.APPROVED;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public void setPromoImage(List<Files> images) {
        // TODO Auto-generated method stub
        this.promotionImages = images;
    }

    public void setMentorProfessor(Professor prof) {
        this.mentor_prof = prof;
    }
}
