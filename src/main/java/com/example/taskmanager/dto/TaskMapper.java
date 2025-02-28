package com.example.taskmanager.dto;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.entity.TaskCategory;
import com.example.taskmanager.enums.TaskStatus; 

public class TaskMapper {

    public static TaskDTO toDTO(Task task) {
        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getCategory().getId()
        );
    }

    public static Task toEntity(TaskDTO taskDTO, TaskCategory category) {
        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(taskDTO.getStatus() != null ? taskDTO.getStatus() : TaskStatus.PENDING); 
        task.setCategory(category);
        return task;
    }
}
