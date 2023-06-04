package com.solo.soloproject.dto;

import com.solo.soloproject.entity.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TodoMapper {

    Todo todoPostDtoToTodo(TodoDto.post todoPostDto);

    Todo todoPatchDtoToTodo(TodoDto.patch todoPatchDto);
    TodoDto.response todoToTodoResponseDto(Todo todo);

    List<TodoDto.response> todoListToResponseDtoList(List<Todo> todoList);
}
