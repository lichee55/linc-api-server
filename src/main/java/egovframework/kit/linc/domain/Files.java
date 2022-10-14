package egovframework.kit.linc.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import egovframework.kit.linc.domain.member.Company;

@Entity
@Table(name = "files")

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Files {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String path;

    private String type;

    private Long size;

    private String ext;

    private String url;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Company companyLogoImageFK;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Company promotionImagesFK;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Notice noticeAttatchFK;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private FieldPracticeCompany businessLicenseFK;

    public void setLogo(Company com) {
        this.companyLogoImageFK = com;
    }

    public void setPromotionImagesFK(Company promotionImagesFK) {
        this.promotionImagesFK = promotionImagesFK;
    }

    public void setNoticeAttatchFK(Notice noticeAttatchFK) {
        this.noticeAttatchFK = noticeAttatchFK;
    }

    public void setBusinessLicenseFK(FieldPracticeCompany businessLicenseFK) {
        this.businessLicenseFK = businessLicenseFK;
    }
}