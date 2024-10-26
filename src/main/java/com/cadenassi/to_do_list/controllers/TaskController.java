package com.cadenassi.to_do_list.controllers;

import com.cadenassi.to_do_list.dto.TaskDTO;
import com.cadenassi.to_do_list.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(name = "/tasks/v1")
public class TaskController {

    @Autowired
    private TaskService service;

    @GetMapping
    public ResponseEntity<List<TaskDTO>> findAll() {

        var tasks = service.findAll();

        return ResponseEntity.ok().body(tasks);
    }


    @GetMapping(value = "/{day}", produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<TaskDTO>> findByDay(@PathVariable("day") String day) {

        var tasks = service.findByDay(day);

        return ResponseEntity.ok().body(tasks);
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<TaskDTO> insert(@RequestBody TaskDTO taskDTO) {

        var task = service.insert(taskDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    @PutMapping(value = "/{day}/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<TaskDTO> update(@RequestBody TaskDTO taskDTO, @PathVariable("day") String day,
                                          @PathVariable("id") String id) {

        var task = service.update(taskDTO, id, day);

        return ResponseEntity.ok().body(task);
    }

    @PutMapping(value = "/completed/{day}/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<TaskDTO> updateCompleted(@PathVariable("day") String day,@PathVariable("id") String id) {

        var task = service.updateCompleted(day, id);

        return ResponseEntity.ok().body(task);
    }

    @DeleteMapping(value = "/{day}/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Void> delete(@PathVariable("day") String day,@PathVariable("id") String id) {

        service.delete(day, id);

        return ResponseEntity.noContent().build();
    }
}
