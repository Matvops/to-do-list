package com.cadenassi.to_do_list.mappers;

import com.cadenassi.to_do_list.domain.Task;
import com.cadenassi.to_do_list.dto.TaskDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author Matheus
 */

@Mapper(componentModel = "spring")
public interface TaskMapper {

    Task toTask(TaskDTO task);

    @Mapping(source = "id", target = "id")
    TaskDTO toDTO(Task task);

    List<Task> toTasks(List<TaskDTO> tasks);

    List<TaskDTO> toDTOs(List<Task> tasks);
}
