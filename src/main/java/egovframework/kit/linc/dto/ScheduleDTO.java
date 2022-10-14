package egovframework.kit.linc.dto;

import egovframework.kit.linc.domain.Schedule;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ScheduleDTO {
    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;

    public static Schedule toEntity(ScheduleDTO dto) {
        return Schedule.builder()
                .id(dto.getId())
                .name(dto.getName())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .build();
    }

    public static ScheduleDTO of(Schedule schedule) {
        return ScheduleDTO.builder()
                .id(schedule.getId())
                .name(schedule.getName())
                .startDate(schedule.getStartDate())
                .endDate(schedule.getEndDate())
                .build();
    }
}
