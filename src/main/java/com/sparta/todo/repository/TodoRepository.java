package com.sparta.todo.repository;

import com.sparta.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    Optional<Todo> findByTitle(String title);

    Todo findTodoByTitle(String title);
    Optional<Todo> findById(Long id);
}
