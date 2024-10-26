package com.cadenassi.to_do_list.repositories;

import com.cadenassi.to_do_list.domain.Task;
import com.cadenassi.to_do_list.enums.DayEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE CAST(t.day AS string) LIKE LOWER(CONCAT('%', :day, '%'))")
    List<Task> findByDayEnum(@Param("day")String day);

}
