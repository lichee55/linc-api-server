package egovframework.kit.linc.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "tech_care_request")
@DiscriminatorValue("TECH")
@SuperBuilder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TechCareRequest extends CollaboRequest {

    @Column(name = "cooperation_type_etc")
    private String cooperationTypeEtc;

    @Column(name = "wish_mentor")
    private String wishMentor;

    @Column(name = "expect_effect")
    private String expectEffect;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "request")
    @ToString.Exclude
    private List<RequestSupportField> fields;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "request")
    @ToString.Exclude
    private List<RequestCooperationType> types;

    public void setTypes(List<RequestCooperationType> types) {
        this.types = types;
    }

    public void setFields(List<RequestSupportField> fields) {
        this.fields = fields;
    }

}
