package io.github.mennakhalilselim.taskmanagementsystem.model.entity;

import io.github.mennakhalilselim.taskmanagementsystem.model.entity.base.BaseEntity;
import io.github.mennakhalilselim.taskmanagementsystem.model.type.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
@ToString
public class Task extends BaseEntity {
    @Id
    @Column(updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private TaskStatus status = TaskStatus.OPEN;

    private String url;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;
}
