package com.cadenassi.to_do_list.domain;

import com.cadenassi.to_do_list.enums.DayEnum;
import com.cadenassi.to_do_list.enums.PriorityEnum;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tasks")
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "boolean default false")
    private boolean completed;

    @Column(nullable = false)
    private DayEnum day;

    @Column(columnDefinition = "enum default MODERADO")
    private PriorityEnum priority;

    public Task() {
    }

    public Task(String name, boolean completed, DayEnum day, PriorityEnum priority) {
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
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
