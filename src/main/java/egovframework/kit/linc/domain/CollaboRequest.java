package egovframework.kit.linc.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

import egovframework.kit.linc.domain.member.Company;
import egovframework.kit.linc.domain.member.Member;
import egovframework.kit.linc.domain.member.Officer;
import egovframework.kit.linc.domain.member.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "collabo_requests")

@Getter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder

@ToString

public class CollaboRequest {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dtype")
    private String dtype;

    @Column(name = "request_name")
    private String requestName;

    @Column(name = "detail")
    private String detail;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_status")
    private Status status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "rejecte_comment")
    private String rejectComment;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    public void changeStatus(Status status) {
        this.status = status;
    }

    public void setCompany(Company com) {
        this.company = com;
    }

    public void setCreatedAt(LocalDateTime time) {
        this.createdAt = time;
    }

    public void setDtype(String s) {
        this.dtype = s;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setRejectComment(String rejectComment) {
        this.rejectComment = rejectComment;
    }
}
