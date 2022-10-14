package egovframework.kit.linc.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "project")
@Getter
@Builder
@ToString

@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue
    @Column(name = "project_id")
    private Long id;

    @Column(name = "project_name")
    private String name;

    @OneToOne
    @JoinColumn(name = "request_id")
    private CollaboRequest collaboRequest;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

}
