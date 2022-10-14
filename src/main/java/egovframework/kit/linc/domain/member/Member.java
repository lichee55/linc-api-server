package egovframework.kit.linc.domain.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
@NoArgsConstructor
@Getter

public abstract class Member {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    // 회사일 경우 회사 이름
    @Column(name = "name")
    private String name;

    @Column(name = "loginId")
    private String loginId;

    @Column(name = "password")
    private String password;

    // 회사일 경우 담당자 휴대폰 번호
    @Column(name = "phone_number")
    private String phoneNumber;

    // 회사일 경우 담당자 이메일
    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    public void setPassword(String password) {
        this.password = password;
    }
}
