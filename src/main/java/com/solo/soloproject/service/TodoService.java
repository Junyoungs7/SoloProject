package com.solo.soloproject.service;

import com.solo.soloproject.entity.Todo;

import java.util.List;

public interface TodoService {

    public Todo createTodo(Todo create);

    public List<Todo> getTodoLists();

    public Todo getTodo(Long id);

    public Todo patchTodo(Long id, Todo update);

    public void deleteTodos();

    public void deleteTodo(Long id);

}
