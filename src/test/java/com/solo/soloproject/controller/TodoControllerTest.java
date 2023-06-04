package com.solo.soloproject.controller;

import com.google.gson.Gson;
import com.solo.soloproject.dto.TodoDto;
import com.solo.soloproject.dto.TodoMapper;
import com.solo.soloproject.entity.Todo;
import com.solo.soloproject.service.TodoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(TodoController.class)
@SpringBootTest
@AutoConfigureMockMvc
class TodoControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private TodoServiceImpl todoService;

    @Autowired
    private TodoMapper todoMapper;



    @Test
    void postTodo() throws Exception {

        // given
        TodoDto.post post = new TodoDto.post(1, "title", 1, false);
        Todo todo = todoMapper.todoPostDtoToTodo(post);
        String content = gson.toJson(todo);
        given(todoService.createTodo(Mockito.any(Todo.class))).willReturn(todo);

        // when
        ResultActions actions =
                mockMvc.perform(
                        post("/")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        // then
        actions
                .andExpect(status().isCreated());
    }

    @Test
    void getTodoList() {
    }
}