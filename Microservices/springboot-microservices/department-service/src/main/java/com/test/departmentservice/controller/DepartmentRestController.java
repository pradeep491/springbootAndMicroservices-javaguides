package com.test.departmentservice.controller;

import com.test.departmentservice.dto.DepartmentDTO;
import com.test.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/departments")
@AllArgsConstructor
public class DepartmentRestController {

    private DepartmentService service;

    //Build Save Department Rest API
    @PostMapping
    public ResponseEntity<DepartmentDTO> saveDepartment(@RequestBody DepartmentDTO departmentdto) {
        DepartmentDTO dto = service.saveDepartment(departmentdto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    //Build Department Get Rest API.
    @GetMapping("/{code}")
    public ResponseEntity<DepartmentDTO> getDepartment(@PathVariable("code") String departmentCode){
        DepartmentDTO dto = service.getDepartmentByDepatCode(departmentCode);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

}
