package com.example.TaskFlow.service;

import com.example.TaskFlow.dto.TaskRequest;
import com.example.TaskFlow.exception.ResourceNotFoundException;
import com.example.TaskFlow.model.Task;
import com.example.TaskFlow.model.TaskStatus;
import com.example.TaskFlow.model.User;
import com.example.TaskFlow.repository.TaskRepository;
import com.example.TaskFlow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(TaskRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Task task = new Task();
        task.setName(request.getName());
        task.setDescription(request.getDescription());
        task.setDueDate(request.getDateTime());
        task.setPriority(request.getTaskPriority());
        task.setUser(user);
        task.setStatus(TaskStatus.todo);
        return taskRepository.save(task);
    }

}
