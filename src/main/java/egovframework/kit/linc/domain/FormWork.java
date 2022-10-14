package egovframework.kit.linc.domain;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FormWork {

    // 정규 근로시간 1일 기준
    private String timePerDay;
    // 정규 근로시간 1주 기준
    private String timePerWeek;

    // 정규 근로일수 주 n일
    private String dayPerWeek;
    // 정규 근로요일 (월, 화, 수, ..., 금, 토, 일)
    private String workingDay;

}
