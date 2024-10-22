package com.cadenassi.to_do_list.mappers;

import com.cadenassi.to_do_list.domain.Task;
import com.cadenassi.to_do_list.dto.TaskDTO;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    Task toTask(TaskDTO task);

    TaskDTO toDTO(Task task);

    List<Task> toTasks(List<TaskDTO> tasks);

    List<TaskDTO> toDTOs(List<Task> tasks);
}
