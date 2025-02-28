package com.example.taskmanager.config;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.entity.TaskCategory;
import com.example.taskmanager.enums.TaskStatus;
import com.example.taskmanager.repository.TaskCategoryRepository;
import com.example.taskmanager.repository.TaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DatabaseSeeder {

    @Bean
    CommandLineRunner seedDatabase(TaskCategoryRepository categoryRepository, TaskRepository taskRepository) {
        return args -> {
            if (categoryRepository.count() == 0) {
                TaskCategory workCategory = new TaskCategory(null, "Work", null);
                TaskCategory personalCategory = new TaskCategory(null, "Personal", null);

                categoryRepository.saveAll(List.of(workCategory, personalCategory));

                Task task1 = new Task(null, "Finish report", "Monthly report for the manager", TaskStatus.IN_PROGRESS, workCategory);
                Task task2 = new Task(null, "Buy groceries", "Milk, bread, eggs, and coffee", TaskStatus.PENDING, personalCategory);
                Task task3 = new Task(null, "Review code", "Refactor authentication class", TaskStatus.COMPLETED, workCategory);

                taskRepository.saveAll(List.of(task1, task2, task3));

                System.out.println("Database seeded with initial categories and tasks.");
            }
        };
    }
}
