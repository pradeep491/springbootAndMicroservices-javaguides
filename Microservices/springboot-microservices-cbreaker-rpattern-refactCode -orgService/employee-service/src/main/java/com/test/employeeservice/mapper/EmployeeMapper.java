package com.test.employeeservice.mapper;

import com.test.employeeservice.dto.EmployeeDTO;
import com.test.employeeservice.entities.Employee;

public class EmployeeMapper {
    public static EmployeeDTO maptoEmployeeDTO(Employee emp) {
        EmployeeDTO empDTO = new EmployeeDTO(
                emp.getId(),
                emp.getFirstName(),
                emp.getLastName(),
                emp.getEmail(),
                emp.getDepartmentCode(),
                emp.getOrganizationCode()
        );
        return empDTO;
    }
    public static Employee maptoEmployee(EmployeeDTO empDTO) {
        Employee emp = new Employee(
                empDTO.getId(),
                empDTO.getFirstName(),
                empDTO.getLastName(),
                empDTO.getEmail(),
                empDTO.getDepartmentCode(),
                empDTO.getOrganizationCode()
        );
        return emp;
    }
}
