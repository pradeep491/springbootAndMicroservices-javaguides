package com.test.controllers;

import com.test.model.DepartmentDTO;
import com.test.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;


    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> saveDepartment(@RequestBody DepartmentDTO departmentDTO) {
        return new ResponseEntity<>(departmentService.createDepartment(departmentDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long id) {
        return new ResponseEntity<>(departmentService.getDepartmentById(id), HttpStatus.OK);
    }

    @GetMapping("/getAllDepartments")
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        return new ResponseEntity<>(departmentService.getAllDepartments(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable Long id,
                                                          @RequestBody DepartmentDTO departmentDTO) {
        return new ResponseEntity<>(departmentService.updateDepartment(id, departmentDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id) {
        return new ResponseEntity<>(departmentService.deleteDepartment(id),HttpStatus.OK);
    }
}
