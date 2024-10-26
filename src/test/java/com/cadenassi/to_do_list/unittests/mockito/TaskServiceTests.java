package com.cadenassi.to_do_list.unittests.mockito;

import com.cadenassi.to_do_list.domain.Task;
import com.cadenassi.to_do_list.dto.TaskDTO;

import com.cadenassi.to_do_list.enums.DayEnum;
import com.cadenassi.to_do_list.mappers.TaskMapper;
import com.cadenassi.to_do_list.repositories.TaskRepository;
import com.cadenassi.to_do_list.services.TaskService;
import com.cadenassi.to_do_list.unittests.mockito.mocks.TaskMock;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TaskServiceTests {

    @InjectMocks
    private TaskService service;

    @Mock
    private TaskRepository repository;

    @Mock
    private TaskMapper mapper;

    private TaskMock mock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mock = new TaskMock();
    }


    @Order(0)
    @Test
    public void insert(){
        //PREPARATION
        Task task = mock.createTask();
        TaskDTO taskDTO = mock.createTaskDTO();

        when(mapper.toTask(taskDTO)).thenReturn(task);
        when(repository.save(task)).thenReturn(task);
        when(mapper.toDTO(task)).thenReturn(taskDTO);

        //ACTION
        var obj = service.insert(taskDTO);

        //VALIDATION
        assertNotNull(obj);
        assertEquals("TASK1", obj.getName());
    }

    @Order(1)
    @Test
    public void findAll(){
        //PREPARATION
        List<Task> tasks = mock.createTasks();
        List<TaskDTO> DTOs = mock.createTasksDTO();

        when(repository.findAll()).thenReturn(tasks);
        when(mapper.toDTOs(tasks)).thenReturn(DTOs);


        //ACTION
        var obj = service.findAll();

        //VALIDATION
        assertEquals(7, obj.size());
        assertFalse(obj.getFirst().isCompleted());
        assertTrue(obj.getFirst().getName().contains("TASK1"));
    }

    @Order(2)
    @Test
    public void findByDay(){
        //PREPARATION
        List<Task> tasks = mock.createTasks();
        List<TaskDTO> DTOs = mock.createTasksDTO();

        tasks.removeIf(x -> x.getDay() != DayEnum.SEGUNDA);
        DTOs.removeIf(x -> x.getDay() != DayEnum.SEGUNDA);

        when(repository.findByDayEnum("SEGUNDA")).thenReturn(tasks);
        when(mapper.toDTOs(tasks)).thenReturn(DTOs);

        //ACTION
        var obj = service.findByDay(DayEnum.SEGUNDA.name());


        //VALIDATION
        assertEquals(1, obj.size());
        assertEquals(DayEnum.SEGUNDA, obj.getFirst().getDay());
    }


    @Order(3)
    @Test
    public void update(){
        //PREPARATION
        List<Task> tasks = mock.createTasks();
        List<TaskDTO> DTOs = mock.createTasksDTO();
        TaskDTO taskDTO = mock.createTaskDTO(2);

        tasks.removeIf(x -> x.getDay() != DayEnum.DOMINGO);
        DTOs.removeIf(x -> x.getDay() != DayEnum.DOMINGO);

        when(repository.findByDayEnum(DayEnum.DOMINGO.name())).thenReturn(tasks);
        when(mapper.toDTOs(tasks)).thenReturn(DTOs);
        when(repository.findById(taskDTO.getId())).thenReturn(Optional.of(tasks.getFirst()));
        when(repository.save(tasks.getFirst())).thenReturn(tasks.getFirst());
        when(mapper.toDTO(tasks.getFirst())).thenReturn(taskDTO);

        //ACTION
        var obj = service.update(taskDTO, "1", "DOMINGO");

        //VALIDATION
        assertNotNull(obj);
        assertEquals(DayEnum.SEGUNDA, obj.getDay());
    }

    @Order(4)
    @Test
    public void updateCompleted(){
        List<Task> tasks = mock.createTasks();
        List<TaskDTO> DTOs = mock.createTasksDTO();
        TaskDTO taskDTO = mock.createTaskDTO();

        tasks.removeIf(x -> x.getDay() != DayEnum.DOMINGO);
        DTOs.removeIf(x -> x.getDay() != DayEnum.DOMINGO);

        when(repository.findByDayEnum(DayEnum.DOMINGO.name())).thenReturn(tasks);
        when(mapper.toDTOs(tasks)).thenReturn(DTOs);
        when(repository.findById(taskDTO.getId())).thenReturn(Optional.of(tasks.getFirst()));
        when(repository.save(tasks.getFirst())).thenReturn(tasks.getFirst());
        taskDTO.setCompleted(!taskDTO.isCompleted());
        when(mapper.toDTO(tasks.getFirst())).thenReturn(taskDTO);


        var obj = service.updateCompleted("DOMINGO", "1");

        assertNotNull(obj);
        assertTrue(obj.isCompleted());
    }

}
