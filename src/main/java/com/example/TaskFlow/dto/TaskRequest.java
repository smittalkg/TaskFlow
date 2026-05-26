package com.example.TaskFlow.dto;

import com.example.TaskFlow.model.Task;
import com.example.TaskFlow.model.TaskPriority;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskRequest {
    @NotBlank
    private String name;
    private String description;
    @Future
    private LocalDateTime dateTime;
    @NotNull
    private TaskPriority taskPriority;
    @NotNull
    private Integer userId;
}
