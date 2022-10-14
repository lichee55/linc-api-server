package egovframework.kit.linc.domain.member;

import javax.persistence.*;

@Entity
@Table(name = "officers")
@DiscriminatorValue("O")
public class Officer extends Member {
}