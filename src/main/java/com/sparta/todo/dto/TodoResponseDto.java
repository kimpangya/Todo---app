package com.sparta.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@RequiredArgsConstructor
public class TodoResponseDto {
    private String title;
    private String contents;
    private Date date;
    private boolean complete;
    private String user_id;

    public TodoResponseDto(String user_id,String title, String contents, Date date) {
        this.user_id=user_id;
        this.title=title;
        this.contents=contents;
        this.date=date;
    }

}
