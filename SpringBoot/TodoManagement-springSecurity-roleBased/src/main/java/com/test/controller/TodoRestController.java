package com.test.controller;

import com.test.dto.TodoDTO;
import com.test.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoRestController {

    private final TodoService todoService;


    public TodoRestController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<TodoDTO> createTodo(@RequestBody TodoDTO todoDTO) {
        return new ResponseEntity<>(todoService.createTodo(todoDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoDTO> getTodoById(@PathVariable Long id) {
        return new ResponseEntity<>(todoService.getTodoById(id), HttpStatus.OK);
    }

    @GetMapping("/getAllTodos")
    public ResponseEntity<List<TodoDTO>> getAllTodos() {
        return new ResponseEntity<>(todoService.getAllTodos(), HttpStatus.OK);
    }

    @PutMapping("/{id}/updateTodo")
    public ResponseEntity<TodoDTO> updateTodo(@PathVariable Long id,
                                              @RequestBody TodoDTO todoDTO) {
        return new ResponseEntity<>(todoService.updateTodo(id, todoDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id) {
        return new ResponseEntity<>(todoService.deleteTodo(id), HttpStatus.OK);
    }

    //Build Complete Todo REST API
    @PatchMapping("/{id}/complete")
    public ResponseEntity<TodoDTO> completeTodo(@PathVariable Long id){
        return new ResponseEntity<>(todoService.completeTodo(id),HttpStatus.OK);
    }

    //Build InComplete Todo REST API
    @PatchMapping("/{id}/in-complete")
    public ResponseEntity<TodoDTO> inCompleteTodo(@PathVariable Long id){
        return new ResponseEntity<>(todoService.inCompleteTodo(id),HttpStatus.OK);
    }
}
