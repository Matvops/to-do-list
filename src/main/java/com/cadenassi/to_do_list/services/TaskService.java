package com.cadenassi.to_do_list.services;

import com.cadenassi.to_do_list.dto.TaskDTO;
import com.cadenassi.to_do_list.enums.DayEnum;
import com.cadenassi.to_do_list.mappers.TaskMapper;
import com.cadenassi.to_do_list.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    @Autowired
    private TaskMapper mapper;

    public TaskDTO findAll(){

    }

    public TaskDTO findByDay(String day){

    }

    public TaskDTO insert(TaskDTO){

    }

    public TaskDTO update(TaskDTO taskDTO, String id){

    }

    public TaskDTO updateCompleted(TaskDTO taskDTO, String id){

    }

    public void delete(String day, String id){

    }


}
