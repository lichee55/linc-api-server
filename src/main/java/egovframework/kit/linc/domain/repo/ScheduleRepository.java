package egovframework.kit.linc.domain.repo;


import egovframework.kit.linc.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {


    List<Schedule> findAllByStartDateAfterAndEndDateBefore(LocalDate startDate, LocalDate endDate);
}
