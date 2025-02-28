package com.example.taskmanager.entity;

import com.example.taskmanager.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 500)
    private String description;

    @Enumerated(EnumType.STRING)  
    @Column(nullable = false)
    private TaskStatus status = TaskStatus.PENDING;  

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private TaskCategory category;
}
