package com.example.TaskFlow.controller;

import com.example.TaskFlow.dto.TaskRequest;
import com.example.TaskFlow.model.Task;
import com.example.TaskFlow.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    private ResponseEntity<Task> addTask(@Valid @RequestBody TaskRequest request) {
        Task saved = taskService.createTask(request);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

}
