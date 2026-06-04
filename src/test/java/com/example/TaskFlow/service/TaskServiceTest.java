package com.example.TaskFlow.service;

import com.example.TaskFlow.dto.TaskRequest;
import com.example.TaskFlow.dto.TaskResponse;
import com.example.TaskFlow.exception.ResourceNotFoundException;
import com.example.TaskFlow.mapper.TaskMapper;
import com.example.TaskFlow.model.Task;
import com.example.TaskFlow.model.TaskPriority;
import com.example.TaskFlow.model.TaskStatus;
import com.example.TaskFlow.model.User;
import com.example.TaskFlow.repository.TaskRepository;
import com.example.TaskFlow.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private TaskService taskService;

    @Test
    void createTask_whenUserNotFound_throwsResourceNotFoundException() {
        // Arrange
        TaskRequest request = new TaskRequest();
        request.setUserId(99);

        when(userRepository.findById(99)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class,
                () -> taskService.createTask(request));
    }

    @Test
    void createTask_whenUserExists_returnsTaskResponse() {
        User user = new User();
        user.setId(1);
        user.setEmail("test@test.com");

        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setName("Test");
        taskRequest.setUserId(1);
        taskRequest.setTaskPriority(TaskPriority.HIGH);

        Task task = new Task();
        task.setId(1);
        task.setName("Test");
        task.setPriority(TaskPriority.HIGH);
        task.setStatus(TaskStatus.todo);
        task.setUser(user);

        TaskResponse expectedTaskResponse = new TaskResponse();
        expectedTaskResponse.setId(1);
        expectedTaskResponse.setName("Test");
        expectedTaskResponse.setUserId(1);

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(taskRepository.save(any(Task.class))).thenReturn(task);
        when(taskMapper.taskToTaskResponse(task)).thenReturn(expectedTaskResponse);

        TaskResponse response = taskService.createTask(taskRequest);

        assertThat(response.getId()).isEqualTo(expectedTaskResponse.getId());
        assertThat(response.getName()).isEqualTo(expectedTaskResponse.getName());
        assertThat(response.getUserId()).isEqualTo(expectedTaskResponse.getUserId());
    }
}