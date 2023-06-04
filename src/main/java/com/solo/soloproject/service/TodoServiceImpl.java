package com.solo.soloproject.service;

import com.solo.soloproject.entity.Todo;
import com.solo.soloproject.exception.BusinessLoginException;
import com.solo.soloproject.exception.ExceptionCode;
import com.solo.soloproject.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{

    private final TodoRepository todoRepository;

    @Override
    public Todo createTodo(Todo create) {

        if(existTodo(create.getId()))
            return patchTodo(create.getId(), create);
        else
            return todoRepository.save(create);
    }

    @Override
    public List<Todo> getTodoLists() {
        return todoRepository.findAll();
    }

    @Override
    public Todo getTodo(Long id) {
        return findVerifiedTodo(id);
    }

    @Override
    public Todo patchTodo(Long id, Todo updateTodo) {
        Todo findTodo = findVerifiedTodo(id);
        findTodo.update(updateTodo.getTitle(), updateTodo.getOrder(), updateTodo.isCompleted());
        return todoRepository.save(findTodo);
    }

    @Transactional(readOnly = true)
    public Todo findVerifiedTodo(long id){
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        return optionalTodo.orElseThrow(() -> new BusinessLoginException(ExceptionCode.TODO_NOT_FOUND));
    }

    @Override
    public void deleteTodos() {
        todoRepository.deleteAll();
    }

    @Override
    public void deleteTodo(Long id) {
        Todo todo = findVerifiedTodo(id);

        todoRepository.delete(todo);
    }

    private boolean existTodo(Long id) {
        Optional<Todo> todo = todoRepository.findById(id);
        return todo.isPresent();
    }


}
