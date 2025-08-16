package com.test.departmentservice.controller;

import com.test.departmentservice.dto.DepartmentDTO;
import com.test.departmentservice.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Department Controller",
        description = "Department Controller exposes REST API's for Department Service"
)
@RestController
@RequestMapping("/api/departments")
@AllArgsConstructor
public class DepartmentRestController {

    private DepartmentService service;

    //Build Save Department Rest API
    //http://localhost:8080/api/departments
    @Operation(
            summary = "Summary Department REST API",
            description = "save Department REST API is used to save Department Object in a DB"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 Created"
    )
    @PostMapping
    public ResponseEntity<DepartmentDTO> saveDepartment(@RequestBody DepartmentDTO departmentdto) {
        DepartmentDTO dto = service.saveDepartment(departmentdto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Summary Department REST API",
            description = "save Department REST API is used to get Department Object from the DB"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    //Build Department Get Rest API.
    //http://localhost:8080/api/departments
    @GetMapping("/{code}")
    public ResponseEntity<DepartmentDTO> getDepartment(@PathVariable("code") String departmentCode) {
        DepartmentDTO dto = service.getDepartmentByDeptCode(departmentCode);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
