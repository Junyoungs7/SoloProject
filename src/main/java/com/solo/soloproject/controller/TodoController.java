package com.solo.soloproject.controller;

import com.solo.soloproject.dto.TodoDto;
import com.solo.soloproject.dto.TodoMapper;
import com.solo.soloproject.entity.Todo;
import com.solo.soloproject.service.TodoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoServiceImpl todoService;
    private final TodoMapper todoMapper;

    @PostMapping("/")
    public ResponseEntity<TodoDto.response> postTodo(@Valid @RequestBody TodoDto.post post) {
        Todo saveTodo = todoService.createTodo(todoMapper.todoPostDtoToTodo(post));

        return new ResponseEntity<>(todoMapper.todoToTodoResponseDto(saveTodo), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<TodoDto.response>> getTodoList() {
        List<Todo> findAll = todoService.getTodoLists();
        if(findAll.size() == 0)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(todoMapper.todoListToResponseDtoList(findAll), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoDto.response> getTodo(@PathVariable("id") long id) {
        Todo findTodo = todoService.getTodo(id);
        return new ResponseEntity<>(todoMapper.todoToTodoResponseDto(findTodo), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TodoDto.response> patchTodo(@PathVariable("id") long id,
                                                      @RequestBody TodoDto.patch patch) {
        Todo updateTodo = todoService.patchTodo(id, todoMapper.todoPatchDtoToTodo(patch));
        return new ResponseEntity<>(todoMapper.todoToTodoResponseDto(updateTodo), HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteTodos(){
        todoService.deleteTodos();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable("id") long id){
        todoService.deleteTodo(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
