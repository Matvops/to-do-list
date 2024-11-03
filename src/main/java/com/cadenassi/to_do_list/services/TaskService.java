package com.cadenassi.to_do_list.services;

import com.cadenassi.to_do_list.controllers.TaskController;
import com.cadenassi.to_do_list.dto.TaskDTO;
import com.cadenassi.to_do_list.exceptions.ObjectIsNullException;
import com.cadenassi.to_do_list.exceptions.ResourceNotFoundException;
import com.cadenassi.to_do_list.mappers.TaskMapper;
import com.cadenassi.to_do_list.repositories.TaskRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Matheus
 */

@Service
public class TaskService {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(TaskService.class);
    Logger logger = Logger.getLogger(TaskService.class.getName());

    @Autowired
    private TaskRepository repository;

    @Autowired
    private TaskMapper mapper;


    /** Find all tasks
     * @return {@code List<TaskDTO>} - returns list of all TaskDTO
     */
    public List<TaskDTO> findAll(){
        logger.info("Find all tasks");

        var tasks = mapper.toDTOs(repository.findAll());
        tasks.forEach(x -> x = addHateoas(x));

        return tasks;
    }

    /** Find tasks filtered by day
     * @param day String - day that will filter tasks
     * @return {@code List<TaskDTO>} - List of tasks filtered
     */
    public List<TaskDTO> findByDay(String day){
        logger.info("Find by day");

        checkString(day);

        String dayEnum = day.toUpperCase();
        var tasks = mapper.toDTOs(repository.findByDayEnum(dayEnum));
        tasks.forEach(x -> x = addHateoas(x));

        return tasks;
    }


    /** Insert a taskDTO in db
     * @param taskDTO TaskDTO - TaskDTO that will be inserted
     * @return {@code TaskDTO} - TaskDTO inserted
     */
    public TaskDTO insert(TaskDTO taskDTO){
        logger.info("Insert a task");

        checkDTO(taskDTO);

        var task = mapper.toDTO(repository.save(mapper.toTask(taskDTO)));
        task = addHateoas(taskDTO);

        return task;
    }


    /** Update a task filtered by day and selected by ID
     * @param taskDTO TaskDTO - task that will be modified
     * @param id String - id that go select
     * @param day String - day that go filter
     * @return {@code TaskDTO} - Returns TaskDTO with modified attributes (which is null, not what will be modified)
     */
    public TaskDTO update(TaskDTO taskDTO, String id, String day){
        logger.info("Update a Task");

        checkDTO(taskDTO);
        checkString(id);
        checkString(day);



        var tasks = findByDay(day);

        var dto = tasks.get(parseStringToInteger(id) - 1);

        var task = repository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("RESOURCE NOT FOUND!"));

        task.setName(taskDTO.getName());
        task.setDay(taskDTO.getDay());
        task.setPriority(taskDTO.getPriority());

        taskDTO = mapper.toDTO(repository.save(task));
        taskDTO = addHateoas(taskDTO);

        return taskDTO;
    }

    /** Updates the task's 'completed' attribute
     * @param day String - day that go filter
     * @param id String - id that go select
     * @return {@code TaskDTO} - Returns taskDTO with 'completed' attribute modified
     */
    public TaskDTO updateCompleted(String day, String id){
        logger.info("Update variable Completed of Task");

        checkString(day);
        checkString(id);

        var tasks = findByDay(day);

        var dto = tasks.get(parseStringToInteger(id) - 1);

        var task = repository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("RESOURCE NOT FOUND!"));


        task.setCompleted(!task.isCompleted());

        var taskDTO = mapper.toDTO(repository.save(task));
        taskDTO = addHateoas(taskDTO);

        return taskDTO;
    }


    /**Delete task filtered by day and selected by ID
     *
     * @param day String - day that go filter
     * @param id String - id that go select
     *
     * @return {@code void}
     */
    public void delete(String day, String id){
        logger.info("Delete a Task");

        checkString(day);
        checkString(id);

        var tasks = findByDay(day);

        var dto = tasks.get(parseStringToInteger(id) - 1);

        repository.deleteById(dto.getId());
    }


    /** Add links in task
     * @param taskDTO TaskDTO - taskDTO that will receive links
     * @return taskDTO TaskDTO - taskDTO with links added
     */
    private TaskDTO addHateoas(TaskDTO taskDTO){

        String id = taskDTO.getId().toString();
        String day = taskDTO.getDay().toString();

        taskDTO.add(linkTo(methodOn(TaskController.class).findAll())
                .withRel("findAll"));

        taskDTO.add(linkTo(methodOn(TaskController.class).findByDay(day))
                .withRel("findByDay"));

        taskDTO.add(linkTo(methodOn(TaskController.class).update(taskDTO, id, day))
                .withRel("update"));

        taskDTO.add(linkTo(methodOn(TaskController.class).updateCompleted(day, id))
                .withRel("updateCompleted"));

        taskDTO.add(linkTo(methodOn(TaskController.class).delete(day, id))
                .withRel("delete"));

        return taskDTO;
    }

    /** Checks if String is null or blank
     *
     * @param number String - number that will be checked
     *
     * @throws
     * ObjectIsNullException - if number is null
     *
     * @throws
     * ResourceNotFoundException - if number is blank
     */
    private void checkString(String number) {
        if (number == null) throw new ObjectIsNullException("OBJECT IS NULL!");
        if (number.isBlank()) throw new ResourceNotFoundException("OBJECT IS EMPTY!");
    }


    /** Checks if taskDTO is null
     *
     * @param task TaskDTO - TaskDTO that will be checked
     *
     * @throws
     * ObjectIsNullException - If TaskDTO is null
     */
    private void checkDTO(TaskDTO task){
        if (task == null) throw new ObjectIsNullException("OBJECT IS NULL!");
    }

    /** Checks if String is Integer and parse String to Integer
     *
     * @param number String - number that will be parsed
     *
     * @return {@code Integer} - number parsed
     *
     * @throws
     * IllegalArgumentException - if number not is Integer
     */
    private Integer parseStringToInteger(String number) {
        if (!number.matches("\\d*[0-9]\\d*")) throw new IllegalArgumentException("NOT IS INTEGER!");

        return Integer.parseInt(number);
    }



}
