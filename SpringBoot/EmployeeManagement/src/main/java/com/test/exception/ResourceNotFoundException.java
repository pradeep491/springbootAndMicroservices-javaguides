package com.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    private String resourceName;
    private String fieldName;
    private Long fieldValue;

    public ResourceNotFoundException(String department, String id, Long id1) {
        super(String.format("%s not found with %s:%s", department, id, id1));
        this.resourceName = department;
        this.fieldName = id;
        this.fieldValue = id1;
    }
}
