package egovframework.kit.linc.domain;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeptForm {

    private String deptName;
    private String officerName;
    private String officerPosition;
    private String deptContact;
    private String officerPhone;
    private String email;
    private String faxNumber;
}
