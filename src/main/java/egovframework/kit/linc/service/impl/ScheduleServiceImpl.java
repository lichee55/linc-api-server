package egovframework.kit.linc.service.impl;


import egovframework.kit.linc.domain.Schedule;
import egovframework.kit.linc.domain.repo.ScheduleRepository;
import egovframework.kit.linc.dto.ScheduleDTO;
import egovframework.kit.linc.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Override
    public void addSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = ScheduleDTO.toEntity(scheduleDTO);
        scheduleRepository.save(schedule);
    }

    @Override
    public List<ScheduleDTO> getSchedules(LocalDate date) {
        LocalDate start = date.minusMonths(1).withDayOfMonth(20);
        LocalDate end = date.plusMonths(1).withDayOfMonth(10);

        List<Schedule> schedules = scheduleRepository.findAllByStartDateAfterAndEndDateBefore(start, end);
        return schedules.stream().map(ScheduleDTO::of).collect(Collectors.toList());
    }

    @Override
    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);

    }
}
