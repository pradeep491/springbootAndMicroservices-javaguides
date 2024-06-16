package com.test.springboot.controller;

import com.test.springboot.dto.UserDTO;
import com.test.springboot.entity.User;
import com.test.springboot.exception.ErrorDetails;
import com.test.springboot.exception.ResourceNotFoundException;
import com.test.springboot.mapper.UserMapper;
import com.test.springboot.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
@Tag(name = "CRUD REST API's for User Resource",
description = "CRUD REST API's - Create User,Update User,Get User,Get All Users,Delete User")
public class UserController {
    private final UserService service;

    //build create user rest api
    @Operation(summary = "Create USER REST API",
            description = "Create User REST API to create the User in DB")
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 Created"
    )
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO user) {
        UserDTO savedUser = service.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @Operation(summary = "Get USER By ID REST API",
            description = "GET User BY ID REST API to get the single User from the DB")
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 Success"
    )
    //Build user find by id
    //http://localhost:8080/api/users/{id}
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO dto = service.getUserById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @Operation(summary = "Get ALL USERS REST API",
            description = "GET ALL User's REST API to get all the User's from the DB")
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 Success"
    )
    //http://localhost:8080/api/users/allUsers
    @GetMapping("/allUsers")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = service.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @Operation(summary = "Update USER REST API",
            description = "Update User REST API to update the particular User in the DB")
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 Success"
    )
    //update Rest API
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long userId, @RequestBody @Valid UserDTO user) {
        user.setId(userId);
        UserDTO updateduser = service.updateUser(user);
        return new ResponseEntity<>(updateduser, HttpStatus.OK);
    }
    @Operation(summary = "Delete USER REST API",
            description = "Delete User REST API to delete the particular User from the DB")
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 Success"
    )
    //Build Delete REST API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
        return new ResponseEntity<>("User Successfully deleted...!", HttpStatus.OK);
    }

    /*@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                        WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
                                                     exception.getMessage(),
                                                     request.getDescription(false),
                                            "User Not FOund");
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }*/
}
