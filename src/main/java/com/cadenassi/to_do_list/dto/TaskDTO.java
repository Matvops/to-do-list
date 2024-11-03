package com.cadenassi.to_do_list.dto;

import com.cadenassi.to_do_list.enums.DayEnum;
import com.cadenassi.to_do_list.enums.PriorityEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.RepresentationModel;


import java.io.Serializable;
import java.util.Objects;

/**
 * @author Matheus
 */

public class TaskDTO extends RepresentationModel<TaskDTO> implements Serializable {

    @JsonIgnore
    private Long id;

    private String name;

    private boolean completed;

    private DayEnum day;

    private PriorityEnum priority;

    public TaskDTO() {
    }

    public TaskDTO(Long id, String name, boolean completed, DayEnum day, PriorityEnum priority) {
        setId(id);
        this.name = name;
        this.completed = completed;
        this.day = day;
        this.priority = priority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public DayEnum getDay() {
        return day;
    }

    public void setDay(DayEnum day) {
        this.day = day;
    }

    public PriorityEnum getPriority() {
        return priority;
    }

    public void setPriority(PriorityEnum priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskDTO taskDTO = (TaskDTO) o;
        return Objects.equals(id, taskDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
