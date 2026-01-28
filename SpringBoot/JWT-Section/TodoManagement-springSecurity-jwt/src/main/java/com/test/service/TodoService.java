package com.test.service;

import com.test.dto.TodoDTO;

import java.util.List;

public interface TodoService {
    public TodoDTO createTodo(TodoDTO dto);

    public TodoDTO getTodoById(Long id);

    public List<TodoDTO> getAllTodos();

    public TodoDTO updateTodo(Long id,TodoDTO todoDTO);

    public String deleteTodo(Long id);

    public TodoDTO completeTodo(Long id);

    public TodoDTO inCompleteTodo(Long id);
}
