package com.test.service.impl;

import com.test.dto.TodoDTO;
import com.test.entity.Todo;
import com.test.exception.ResourceNotFoundException;
import com.test.repos.TodoRepository;
import com.test.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    private final ModelMapper modelMapper;

    public TodoServiceImpl(TodoRepository todoRepository, ModelMapper modelMapper) {
        this.todoRepository = todoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TodoDTO createTodo(TodoDTO dto) {
        Todo todo = modelMapper.map(dto, Todo.class);
        Todo savedTodo = todoRepository.save(todo);

        return modelMapper.map(savedTodo, TodoDTO.class);
    }

    @Override
    public TodoDTO getTodoById(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo does not exists with the Id:" + id));

        return modelMapper.map(todo, TodoDTO.class);
    }

    @Override
    public List<TodoDTO> getAllTodos() {
        List<Todo> todosList = todoRepository.findAll();
        return todosList.stream().map(todo -> modelMapper.map(todo, TodoDTO.class)).toList();
    }

    @Override
    public TodoDTO updateTodo(Long id, TodoDTO todoDTO) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo does not exists with the Id:" + id));

        Todo todo1 = new Todo();
        todo1.setId(todo.getId());
        todo1.setDescription(todo.getDescription());
        todo1.setCompleted(todo.isCompleted());

        Todo updatedTodo = todoRepository.save(todo1);
        return modelMapper.map(updatedTodo, TodoDTO.class);
    }

    @Override
    public String deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo does not exists with the Id:" + id));
        todoRepository.delete(todo);
        return "Todo activity with the id:" + id + " gets deleted successfully";
    }

    @Override
    public TodoDTO completeTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo does not exists with the Id:" + id));
        todo.setCompleted(Boolean.TRUE);

        Todo updatedTodo = todoRepository.save(todo);
        return modelMapper.map(updatedTodo, TodoDTO.class);
    }

    @Override
    public TodoDTO inCompleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo does not exists with the Id:" + id));
        todo.setCompleted(Boolean.FALSE);

        Todo updatedTodo = todoRepository.save(todo);
        return modelMapper.map(updatedTodo, TodoDTO.class);
    }
}
