package com.sparta.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
public class TodoListResponseDto {
    private String title;
    private String contents;
    private Date date;
    private boolean complete;
    private String user_id;
}
