package com.test.employeeservice.service.impl;

import com.test.employeeservice.dto.EmployeeDTO;
import com.test.employeeservice.entities.Employee;
import com.test.employeeservice.repos.EmployeeRepository;
import com.test.employeeservice.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repo;

    public EmployeeServiceImpl(EmployeeRepository repo) {
        this.repo = repo;
    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO dto) {
        Employee e = new Employee(dto.getId(), dto.getFirstName(),
                dto.getLastName(), dto.getEmail());
        Employee savedEmployee = repo.save(e);

        EmployeeDTO dto1 = new EmployeeDTO(savedEmployee.getId(),
                savedEmployee.getFirstName(),
                savedEmployee.getLastName(),
                savedEmployee.getEmail());
        return dto1;
    }

    @Override
    public EmployeeDTO getEmployeeByEmpid(Long empid) {
        Employee e = repo.findById(empid).get();
        EmployeeDTO dto = new EmployeeDTO(e.getId(),e.getFirstName(),e.getLastName(),e.getEmail());
        return dto;

    }


}
