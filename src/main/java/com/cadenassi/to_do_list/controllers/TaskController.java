package com.cadenassi.to_do_list.controllers;

import com.cadenassi.to_do_list.dto.TaskDTO;
import com.cadenassi.to_do_list.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(value = "/tasks/v1")
@Tag(name = "Tasks", description = "Controller of tasks")
public class TaskController {

    @Autowired
    private TaskService service;

    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Find all tasks", description = "Find all tasks of all days", tags = {"Tasks"}, responses = {
            @ApiResponse(description = "SUCCESS", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDTO.class))),
            @ApiResponse(description = "INTERNAL ERROR", responseCode = "500", content = @Content(schema = @Schema))
    })
    public ResponseEntity<List<TaskDTO>> findAll() {

        var tasks = service.findAll();

        return ResponseEntity.ok().body(tasks);
    }

    @GetMapping(value = "/{day}", produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Find tasks by day", description = "Find all tasks of selected day", tags = {"Tasks"}, responses = {
            @ApiResponse(description = "SUCCESS", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDTO.class))),
            @ApiResponse(description = "NOT FOUND", responseCode = "404", content = @Content),
            @ApiResponse(description = "INTERNAL ERROR", responseCode = "500", content = @Content)
    })
    public ResponseEntity<List<TaskDTO>> findByDay(@PathVariable("day") String day) {

        var tasks = service.findByDay(day);

        return ResponseEntity.ok().body(tasks);
    }


    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Insert task", description = "Insert task", tags = {"Tasks"}, responses = {
            @ApiResponse(description = "CREATED", responseCode = "201",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDTO.class))),
            @ApiResponse(description = "NOT FOUND", responseCode = "404", content = @Content(schema = @Schema)),
            @ApiResponse(description = "INTERNAL ERROR", responseCode = "500", content = @Content(schema = @Schema))
    })
    public ResponseEntity<TaskDTO> insert(@RequestBody TaskDTO taskDTO) {

        var task = service.insert(taskDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    @PutMapping(value = "/{day}/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Update task by id", description = "Updates attributes except completed", tags = {"Tasks"},
            responses = {
                    @ApiResponse(description = "SUCCESS", responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDTO.class))),
                    @ApiResponse(description = "NOT FOUND", responseCode = "404", content = @Content(schema = @Schema)),
                    @ApiResponse(description = "INTERNAL ERROR", responseCode = "500", content = @Content(schema = @Schema))
            })
    public ResponseEntity<TaskDTO> update(@RequestBody TaskDTO taskDTO, @PathVariable("day") String day,
                                          @PathVariable("id") String id) {

        var task = service.update(taskDTO, id, day);

        return ResponseEntity.ok().body(task);
    }

    @PutMapping(value = "/completed/{day}/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Update task by id", description = "Updates attribute completed", tags = {"Tasks"},
            responses = {
                    @ApiResponse(description = "SUCCESS", responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDTO.class))),
                    @ApiResponse(description = "NOT FOUND", responseCode = "404", content = @Content(schema = @Schema)),
                    @ApiResponse(description = "INTERNAL ERROR", responseCode = "500", content = @Content(schema = @Schema))
            })
    public ResponseEntity<TaskDTO> updateCompleted(@PathVariable("day") String day, @PathVariable("id") String id) {

        var task = service.updateCompleted(day, id);

        return ResponseEntity.ok().body(task);
    }

    @DeleteMapping(value = "/{day}/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Delete task by id", description = "Delete task by id on day", tags = {"Tasks"},
            responses = {
                    @ApiResponse(description = "NO CONTENT", responseCode = "204",
                            content = @Content(schema = @Schema)),
                    @ApiResponse(description = "NOT FOUND", responseCode = "404", content = @Content(schema = @Schema)),
                    @ApiResponse(description = "INTERNAL ERROR", responseCode = "500", content = @Content(schema = @Schema))
            })
    public ResponseEntity<Void> delete(@PathVariable("day") String day, @PathVariable("id") String id) {

        service.delete(day, id);

        return ResponseEntity.noContent().build();
    }
}