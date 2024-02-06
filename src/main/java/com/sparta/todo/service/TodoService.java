package com.sparta.todo.service;

import com.sparta.todo.dto.TodoListResponseDto;
import com.sparta.todo.dto.TodoRequestDto;
import com.sparta.todo.dto.TodoResponseDto;
import com.sparta.todo.entity.Todo;
import com.sparta.todo.entity.User;
import com.sparta.todo.jwt.JwtUtil;
import com.sparta.todo.repository.TodoRepository;
import com.sparta.todo.repository.UserRepository;
import com.sparta.todo.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final JwtUtil jwtUtil;


    public TodoResponseDto createTodo(TodoRequestDto requestDto, User user) {
        String title = requestDto.getTitle();
        String contents = requestDto.getContents();
        Date date = requestDto.getDate();

        Todo todo = new Todo(user,title, contents, date);
        todoRepository.save(todo);

        return new TodoResponseDto(user.getUsername(), title, contents, date);
    }

    @Transactional
    public TodoResponseDto getTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new NullPointerException("해당 제목의 할일카드가 없습니다."));
       // Todo todo=todoRepository.findTodoByTitle(title);
        return new TodoResponseDto(todo.getUser().getUsername(),todo.getTitle(), todo.getContents(), todo.getDate());
    }

//    @Transactional
//    public List<TodoListResponseDto> getTodoList() {
//        List<Todo> list =  todoRepository.findAll();
//        List<TodoListResponseDto> responseDtoList = new ArrayList<>();
//        for(Todo todo : list){
//            TodoListResponseDto responseDto = new TodoListResponseDto(todo.getUser().getUsername(), todo.getTitle(), todo.getContents(), todo.getDate(), todo.getComplete());
//            responseDtoList.add(responseDto);
//        }
//
//        return responseDtoList;
//    }

    @Transactional
    public List<TodoResponseDto> getTodoList() {
        List<Todo> list =  todoRepository.findAll();
        List<TodoResponseDto> responseDtoList = new ArrayList<>();
        for(Todo todo : list){
            TodoResponseDto responseDto = new TodoResponseDto(todo.getUser().getUsername(), todo.getTitle(), todo.getContents(), todo.getDate());
            responseDtoList.add(responseDto);
        }

        return responseDtoList;
    }

    @Transactional
    public TodoResponseDto updateTodoList(Long id, TodoRequestDto requestDto, User user) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new NullPointerException("해당 제목의 할일카드가 없습니다."));
        if(todo.getUser().getId().equals(user.getId())){
           todo.update(requestDto);
        }else{
            throw new IllegalArgumentException("할일카드의 작성자가 아닙니다. 수정 불가능");
        }
        return new TodoResponseDto(todo.getUser().getUsername(),todo.getTitle(), todo.getContents(),todo.getDate());
    }

    @Transactional
    public void completeTodo(Long id, User user) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new NullPointerException("해당 제목의 할일카드가 없습니다."));
        if(todo.getUser().getId().equals(user.getId())){
            todo.changeComplete();
        }else{
            throw new IllegalArgumentException("할일카드의 작성자가 아닙니다. 수정 불가능");
        }
    }
}
