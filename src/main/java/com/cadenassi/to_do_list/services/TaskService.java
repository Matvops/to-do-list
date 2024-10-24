package com.cadenassi.to_do_list.services;

import com.cadenassi.to_do_list.dto.TaskDTO;
import com.cadenassi.to_do_list.enums.DayEnum;
import com.cadenassi.to_do_list.exceptions.ObjectIsNullException;
import com.cadenassi.to_do_list.exceptions.ResourceNotFoundException;
import com.cadenassi.to_do_list.mappers.TaskMapper;
import com.cadenassi.to_do_list.repositories.TaskRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class TaskService {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(TaskService.class);
    Logger logger = Logger.getLogger(TaskService.class.getName());

    @Autowired
    private TaskRepository repository;

    @Autowired
    private TaskMapper mapper;

    public List<TaskDTO> findAll(){
        logger.info("Find all tasks");

        var tasks = mapper.toDTOs(repository.findAll());

        return tasks;
    }

    public List<TaskDTO> findByDay(String day){
        logger.info("Find dy day");

        checkString(day);

        DayEnum dayEnum = DayEnum.valueOf(day.toUpperCase());

        var tasks = mapper.toDTOs(repository.findByDayEnum(dayEnum));

        return tasks;
    }

    public TaskDTO insert(TaskDTO taskDTO){
        logger.info("Insert a task");

        checkDTO(taskDTO);

        var task = mapper.toDTO(repository.save(mapper.toTask(taskDTO)));

        return task;
    }

    public TaskDTO update(TaskDTO taskDTO, String id){
        logger.info("Update a Task");

        checkDTO(taskDTO);
        checkString(id);

        var tasks = findByDay(taskDTO.getDay().name());

        var dto = tasks.get(parseStringToInteger(id) - 1);

        var task = repository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("RESOURCE NOT FOUND!"));

        task.setName(taskDTO.getName());
        task.setDay(taskDTO.getDay());
        task.setPriority(taskDTO.getPriority());

        return mapper.toDTO(repository.save(task));
    }

    public TaskDTO updateCompleted(String day, String id){
        logger.info("Update variable Completed of Task");

        checkString(day);
        checkString(id);

        var tasks = findByDay(day);

        var dto = tasks.get(parseStringToInteger(id) - 1);

        var task = repository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("RESOURCE NOT FOUND!"));

        task.setCompleted(!task.isCompleted());

        return mapper.toDTO(repository.save(task));
    }

    public void delete(String day, String id){
        logger.info("Delete a Task");

        checkString(day);
        checkString(id);

        var tasks = findByDay(day);

        var dto = tasks.get(parseStringToInteger(id) - 1);

        repository.deleteById(dto.getId());
    }

    private void checkString(String number) {
        if (number == null) throw new ObjectIsNullException("OBJECT IS NULL!");
        if (number.isBlank()) throw new ResourceNotFoundException("OBJECT IS EMPTY!");
    }


    private void checkDTO(TaskDTO task){
        if (task == null) throw new ObjectIsNullException("OBJECT IS NULL!");
    }

    private Integer parseStringToInteger(String number) {
        if (!number.matches("\\d*[0-9]\\d*")) throw new IllegalArgumentException("NOT IS INTEGER!");

        return Integer.parseInt(number);
    }



}
