package com.test.employeeservice.controller;

import com.test.employeeservice.dto.APIResponseDTO;
import com.test.employeeservice.dto.EmployeeDTO;
import com.test.employeeservice.entities.Employee;
import com.test.employeeservice.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name="Employee Service-Employee Controller",
        description = "EmployeeRestController exposes REST API'S for Employee Services"
)
@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {

    private EmployeeService service;

    public EmployeeRestController(EmployeeService service) {
        this.service = service;
    }

    @Operation(
            summary="Save Employee REST API",
            description = "Save Employee REST API Is Used to save Employee Object into DB"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 Created"
    )
    //Build Saved Employee API
    //http://localhost:8081/api/employees
    @PostMapping
    public ResponseEntity<EmployeeDTO> saveEmployeeData(@RequestBody EmployeeDTO dto) {
        EmployeeDTO e = service.saveEmployee(dto);
        return new ResponseEntity<>(e, HttpStatus.CREATED);
    }
    @Operation(
            summary="GET Employee REST API",
            description = "GET Employee REST API Is Used to Get Employee Object from DB"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Success"
    )
    @GetMapping("/{empid}")
    public ResponseEntity<APIResponseDTO> getEmpById(@PathVariable Long empid) {
        APIResponseDTO e = service.getEmployeeByEmpid(empid);
        return new ResponseEntity<>(e, HttpStatus.OK);
    }
}
