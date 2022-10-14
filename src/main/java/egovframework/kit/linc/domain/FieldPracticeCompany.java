package egovframework.kit.linc.domain;

import javax.persistence.*;

import egovframework.kit.linc.domain.member.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldPracticeCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 1:코스피 2:코스닥 3:비상장
    @Column(name = "listing_stock")
    private Integer isListingStock;

    @Column(name = "form_work")
    @Embedded
    private FormWork formWork;

    @Embedded
    @Column(name = "management_dept")
    private DeptForm managementDept;

    // 3. 자체 점검표 이하 내용
    @Column
    private String check1;

    @Column
    private String check2;
    @Column
    private String check3;
    @Column
    private String check4;
    @Column
    private String check5;
    @Column
    private String check6;
    @Column
    private String check6_1;
    @Column
    private String check6_2;
    @Column
    private String check7;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Files businessLicense;

    @JoinColumn(name = "company_id")
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Company company;

    public void setBusinessLicense(Files businessLicense) {
        this.businessLicense = businessLicense;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
