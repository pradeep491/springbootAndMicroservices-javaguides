package com.test.employeeservice.service;

import com.test.employeeservice.dto.APIResponseDTO;
import com.test.employeeservice.dto.EmployeeDTO;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {
EmployeeDTO saveEmployee(EmployeeDTO dto);
    APIResponseDTO getEmployeeByEmpid(Long empid);
}
