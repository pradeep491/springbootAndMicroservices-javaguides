package com.test.service;

import com.test.model.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    public EmployeeDTO createEmployee(Long departmentId, EmployeeDTO employeeDTO);

    public EmployeeDTO getEmployeeById(Long departmentId, Long employeeId);

    public List<EmployeeDTO> getAllEmployeesByDepartmentId(Long departmentId);

    public EmployeeDTO updateEmployee(Long departmentId, Long employeeId, EmployeeDTO employeeDTO);

    public String deleteEmployee(Long departmentId, Long employeeId);

}
