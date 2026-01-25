package com.test.service;

import com.test.dto.TodoDTO;

import java.util.List;

public interface TodoService {
    public TodoDTO createTodo(TodoDTO dto);

    public TodoDTO getTodoById(Long id);

    public List<TodoDTO> getAllTodos();
}
