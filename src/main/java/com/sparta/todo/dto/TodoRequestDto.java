package com.sparta.todo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class TodoRequestDto {
    private String title;
    private String contents;
    private Date date;
    private boolean complete;
}
