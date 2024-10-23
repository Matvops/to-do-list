package com.cadenassi.to_do_list.repositories;

import com.cadenassi.to_do_list.domain.Task;
import com.cadenassi.to_do_list.enums.DayEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE t.day LIKE LOWER(CONCAT('%', :day, '%'))")
    Task findByDayEnum(@Param("day")DayEnum day);
}
