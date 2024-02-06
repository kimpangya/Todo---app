package com.sparta.todo.controller;

import com.sparta.todo.dto.TodoListResponseDto;
import com.sparta.todo.dto.TodoRequestDto;
import com.sparta.todo.dto.TodoResponseDto;
import com.sparta.todo.security.UserDetailsImpl;
import com.sparta.todo.service.TodoService;
import com.sparta.todo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/api/todolist")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
    @PostMapping("/create")
    public TodoResponseDto createTodo(@RequestBody TodoRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return todoService.createTodo(requestDto, userDetails.getUser());
    }

    @GetMapping("/todo/{id}")
    public TodoResponseDto getTodo(@PathVariable Long id){
        return todoService.getTodo(id);
    }

    @GetMapping
    public List<TodoResponseDto> getTodoList(){
        return todoService.getTodoList();
    }

    @PutMapping("update/{id}")
    public TodoResponseDto updateTodo(@PathVariable Long id, @RequestBody TodoRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return todoService.updateTodoList(id, requestDto, userDetails.getUser());
    }

    @PutMapping("complete/{id}")
    public void completeTodo(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        todoService.completeTodo(id, userDetails.getUser());
    }
}
