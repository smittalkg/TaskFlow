package com.example.TaskFlow.service;

import com.example.TaskFlow.dto.TaskRequest;
import com.example.TaskFlow.dto.TaskResponse;
import com.example.TaskFlow.exception.ResourceNotFoundException;
import com.example.TaskFlow.mapper.TaskMapper;
import com.example.TaskFlow.model.Task;
import com.example.TaskFlow.model.TaskStatus;
import com.example.TaskFlow.model.User;
import com.example.TaskFlow.repository.TaskRepository;
import com.example.TaskFlow.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final UserRepository userRepository;

    private final TaskRepository taskRepository;

    private final TaskMapper taskMapper;

    @Transactional
    public TaskResponse createTask(TaskRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Task task = new Task();
        task.setName(request.getName());
        task.setDescription(request.getDescription());
        task.setDueDate(request.getDateTime());
        task.setPriority(request.getTaskPriority());
        task.setUser(user);
        task.setStatus(TaskStatus.todo);
        Task savedTask = taskRepository.save(task);

        return taskMapper.taskToTaskResponse(savedTask);
    }

}
