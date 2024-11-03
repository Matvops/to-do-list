package com.cadenassi.to_do_list.unittests.mockito.mocks;

import com.cadenassi.to_do_list.domain.Task;
import com.cadenassi.to_do_list.dto.TaskDTO;
import com.cadenassi.to_do_list.enums.DayEnum;
import com.cadenassi.to_do_list.enums.PriorityEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Matheus
 */

public class TaskMock {

    public Task createTask(){
        return createTask(1);
    }

    public Task createTask(int i){
        Task task = new Task();
        task.setCompleted(false);
        task.setDay(DayEnum.getDay(i));
        task.setName("TASK" + i);
        task.setPriority(PriorityEnum.BAIXA);

        return task;
    }

    public TaskDTO createTaskDTO(){
        return createTaskDTO(1);
    }

    public TaskDTO createTaskDTO(int i){
        TaskDTO task = new TaskDTO();
        task.setCompleted(false);
        task.setDay(DayEnum.getDay(i));
        task.setName("TASK" + i);
        task.setPriority(PriorityEnum.BAIXA);

        return task;
    }

    public List<Task> createTasks(){
        List<Task> tasks = new ArrayList<>();

        for(int i = 1; i < 8; i++){
           tasks.add(new Task("TASK" + i, false, DayEnum.getDay(i), PriorityEnum.BAIXA));
        }

        return tasks;
    }

    public List<TaskDTO> createTasksDTO(){
        List<TaskDTO> tasks = new ArrayList<>();

        for(int i = 1; i < 8; i++){
            tasks.add(new TaskDTO((long) i, "TASK" + i, false, DayEnum.getDay(i), PriorityEnum.BAIXA));
        }

        return tasks;
    }
}
