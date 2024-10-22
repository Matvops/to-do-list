package com.cadenassi.to_do_list.dto;

import com.cadenassi.to_do_list.enums.DayEnum;
import com.cadenassi.to_do_list.enums.PriorityEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;


import java.io.Serializable;
import java.util.Objects;

public class TaskDTO implements Serializable {

    @JsonIgnore
    private Long id;

    private String name;

    private boolean completed;

    private DayEnum day;

    private PriorityEnum priority;

    public TaskDTO() {
    }

    public TaskDTO(String name, boolean completed, DayEnum day, PriorityEnum priority) {
        this.name = name;
        this.completed = completed;
        this.day = day;
        this.priority = priority;
    }

    public Long getId() {
        return id;
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
