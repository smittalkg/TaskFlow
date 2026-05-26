package com.example.TaskFlow.dto;

import com.example.TaskFlow.model.TaskPriority;
import com.example.TaskFlow.model.TaskStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskResponse {
    private Integer id;
    private String name;
    private String description;
    private LocalDateTime dueDate;
    private TaskPriority priority;
    private TaskStatus status;
    private Integer userId;
}
