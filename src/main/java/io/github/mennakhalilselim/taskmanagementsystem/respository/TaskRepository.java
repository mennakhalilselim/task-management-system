package io.github.mennakhalilselim.taskmanagementsystem.respository;

import io.github.mennakhalilselim.taskmanagementsystem.model.entity.Task;
import io.github.mennakhalilselim.taskmanagementsystem.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
    List<Task> findByOwner(User owner);

    Optional<Task> findByIdAndOwner(UUID id, User owner);
}
