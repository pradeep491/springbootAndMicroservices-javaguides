package com.test.controllers;

import com.test.model.EmployeeDTO;
import com.test.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class EmployeeController {

    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/{departmentId}/employees")
    public ResponseEntity<EmployeeDTO> saveEmployee(@PathVariable Long departmentId, @RequestBody EmployeeDTO employeeDTO) {
        return new ResponseEntity<>(employeeService.createEmployee(departmentId, employeeDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{departmentId}/employees/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long departmentId, @PathVariable Long employeeId) {
        return new ResponseEntity<>(employeeService.getEmployeeById(departmentId, employeeId), HttpStatus.OK);
    }

    @GetMapping("/{departmentId}/employees")
    public ResponseEntity<List<EmployeeDTO>> getEmployeeByDepartmentId(@PathVariable Long departmentId) {
        return new ResponseEntity<>(employeeService.getAllEmployeesByDepartmentId(departmentId), HttpStatus.OK);
    }

    @PutMapping("/{departmentId}/employees/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long departmentId
            , @PathVariable Long employeeId
            , @RequestBody EmployeeDTO employeeDTO) {
        return new ResponseEntity<>(employeeService.updateEmployee(departmentId, employeeId, employeeDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{departmentId}/employees/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long departmentId
            , @PathVariable Long employeeId) {
        return new ResponseEntity<>(employeeService.deleteEmployee(departmentId, employeeId), HttpStatus.OK);
    }
}
