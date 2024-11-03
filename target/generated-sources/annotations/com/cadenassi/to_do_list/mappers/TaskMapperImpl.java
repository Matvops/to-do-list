package com.cadenassi.to_do_list.mappers;

import com.cadenassi.to_do_list.domain.Task;
import com.cadenassi.to_do_list.dto.TaskDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

import com.cadenassi.to_do_list.enums.DayEnum;
import com.cadenassi.to_do_list.enums.PriorityEnum;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-02T21:10:17-0300",
    comments = "version: 1.6.2, compiler: javac, environment: Java 21.0.4 (Oracle Corporation)"
)
@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public Task toTask(TaskDTO task) {
        if ( task == null ) {
            return null;
        }

        if(task.getDay().isBlank() || task.getPriority().isBlank())
            return null;

        Task task1 = new Task();

        task1.setName( task.getName() );
        task1.setCompleted( task.isCompleted() );
        task1.setDay(DayEnum.valueOf(task.getDay()));
        task1.setPriority( PriorityEnum.valueOf(task.getPriority()) );

        return task1;
    }

    @Override
    public TaskDTO toDTO(Task task) {
        if ( task == null ) {
            return null;
        }

        TaskDTO taskDTO = new TaskDTO();

        taskDTO.setId( task.getId() );
        taskDTO.setName( task.getName() );
        taskDTO.setCompleted( task.isCompleted() );
        taskDTO.setDay( task.getDay().toString().toUpperCase() );
        taskDTO.setPriority( task.getPriority().toString().toUpperCase() );

        return taskDTO;
    }

    @Override
    public List<Task> toTasks(List<TaskDTO> tasks) {
        if ( tasks == null ) {
            return null;
        }

        List<Task> list = new ArrayList<Task>( tasks.size() );
        for ( TaskDTO taskDTO : tasks ) {
            list.add( toTask( taskDTO ) );
        }

        return list;
    }

    @Override
    public List<TaskDTO> toDTOs(List<Task> tasks) {
        if ( tasks == null ) {
            return null;
        }

        List<TaskDTO> list = new ArrayList<TaskDTO>( tasks.size() );
        for ( Task task : tasks ) {
            list.add( toDTO( task ) );
        }

        return list;
    }
}
