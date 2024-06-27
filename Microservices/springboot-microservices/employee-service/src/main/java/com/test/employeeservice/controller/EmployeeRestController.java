package com.test.employeeservice.controller;

import com.test.employeeservice.dto.EmployeeDTO;
import com.test.employeeservice.entities.Employee;
import com.test.employeeservice.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {

    private EmployeeService service;

    public EmployeeRestController(EmployeeService service) {
        this.service = service;
    }

    //Build Saved Employee API
    @PostMapping
    public ResponseEntity<EmployeeDTO> saveEmployeeData(@RequestBody EmployeeDTO dto) {
        EmployeeDTO e = service.saveEmployee(dto);
        return new ResponseEntity<>(e, HttpStatus.CREATED);
    }

    @GetMapping("/{empid}")
    public ResponseEntity<EmployeeDTO> getEmpById(@PathVariable Long empid) {
        EmployeeDTO e = service.getEmployeeByEmpid(empid);
        return new ResponseEntity<>(e, HttpStatus.OK);
    }
}
