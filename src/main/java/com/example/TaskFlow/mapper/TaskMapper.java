package com.example.TaskFlow.mapper;

import com.example.TaskFlow.dto.TaskResponse;
import com.example.TaskFlow.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(source = "user.id", target = "userId")
    TaskResponse taskToTaskResponse(Task task);
}
