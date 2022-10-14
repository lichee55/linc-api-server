package egovframework.kit.linc.domain.member;

import javax.persistence.*;

@Table(name = "professors")
@Entity
@DiscriminatorValue("P")
public class Professor extends Member {

    @Column(name = "dept_id")
    private Long deptId;

    @Column(name = "address")
    private String address;

}
