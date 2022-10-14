package egovframework.kit.linc.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "schedule")
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {

    @GeneratedValue
    @Id
    @Column(name = "schedule_id")
    private Long id;

    @Column(name = "schedule_name")
    private String name;

    @Column(name = "schedule_start_date")
    private LocalDate startDate;

    @Column(name = "schedule_end_date")
    private LocalDate endDate;

}
