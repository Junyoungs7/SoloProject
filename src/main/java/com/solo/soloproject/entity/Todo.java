package com.solo.soloproject.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, name = "todoOrder")
    private int order;

    @Column(nullable = false)
    private boolean completed = false;

    @Builder
    public Todo(Long id, String title, int order, boolean completed){
        this.id = id;
        this.title = title;
        this.order = order;
        this.completed = completed;
    }

    public void update(String title, int order, boolean completed){
        this.title = title;
        this.order = order;
        this.completed = completed;
    }
}
