package com.example.nguyendhoang.simpletodo.customize;

import java.io.Serializable;

/**
 * Created by Nguyen.D.Hoang on 9/29/2016.
 */
@SuppressWarnings("serial")
public class Schdule implements Serializable{
    private String taskName;
    private String description;
    private String priorityLevel;
    private String Status;

    public Schdule(String taskName, String description, String priorityLevel, String status) {
        this.taskName = taskName;
        this.description = description;
        this.priorityLevel = priorityLevel;
        Status = status;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriorityLevel(String priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getTaskName() {

        return taskName;
    }

    public String getDescription() {
        return description;
    }

    public String getPriorityLevel() {
        return priorityLevel;
    }

    public String getStatus() {
        return Status;
    }
}
