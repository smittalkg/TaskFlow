package com.example.TaskFlow.mapper;

import com.example.TaskFlow.dto.TaskResponse;
import com.example.TaskFlow.model.Task;
import com.example.TaskFlow.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-04T13:21:21+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 26.0.1 (Oracle Corporation)"
)
@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public TaskResponse taskToTaskResponse(Task task) {
        if ( task == null ) {
            return null;
        }

        TaskResponse taskResponse = new TaskResponse();

        taskResponse.setUserId( taskUserId( task ) );
        taskResponse.setId( task.getId() );
        taskResponse.setName( task.getName() );
        taskResponse.setDescription( task.getDescription() );
        taskResponse.setDueDate( task.getDueDate() );
        taskResponse.setPriority( task.getPriority() );
        taskResponse.setStatus( task.getStatus() );

        return taskResponse;
    }

    private Integer taskUserId(Task task) {
        if ( task == null ) {
            return null;
        }
        User user = task.getUser();
        if ( user == null ) {
            return null;
        }
        int id = user.getId();
        return id;
    }
}
