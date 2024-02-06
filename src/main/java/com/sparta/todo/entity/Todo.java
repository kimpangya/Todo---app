package com.sparta.todo.entity;

import com.sparta.todo.dto.TodoRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "todo")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String contents;
    private Date date;
    private boolean complete;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Todo(User user,String title, String contents, Date date) {
        this.title=title;
        this.contents=contents;
        this.date=date;
        this.user=user;
    }

    public void update(TodoRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

    public void changeComplete() {
        this.complete=!this.complete;
    }
}
