package egovframework.kit.linc.service;

import egovframework.kit.linc.dto.ScheduleDTO;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {

    void addSchedule(ScheduleDTO scheduleDTO);

    List<ScheduleDTO> getSchedules(LocalDate date);

    void deleteSchedule(Long id);
}
