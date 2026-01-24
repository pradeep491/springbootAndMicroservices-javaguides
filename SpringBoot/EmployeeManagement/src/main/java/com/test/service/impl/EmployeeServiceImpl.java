package com.test.service.impl;

import com.test.entity.Department;
import com.test.entity.Employee;
import com.test.exception.BadRequestException;
import com.test.exception.ResourceNotFoundException;
import com.test.model.EmployeeDTO;
import com.test.repos.DepartmentRepository;
import com.test.repos.EmployeeRepository;
import com.test.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeServiceImpl(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public EmployeeDTO createEmployee(Long departmentId, EmployeeDTO employeeDTO) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new ResourceNotFoundException("department", "id", departmentId));
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        employee.setDepartment(department);
        Employee savedEmployee = employeeRepository.save(employee);
        return modelMapper.map(savedEmployee, EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO getEmployeeById(Long departmentId, Long employeeId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("department", "id", departmentId));
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("employee", "id", employeeId));
        if (!employee.getDepartment().getId().equals(department.getId())) {
            throw new BadRequestException("Employee does not match with Department Id:" + departmentId);
        }

        EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
        employeeDTO.setDepartmentId(employee.getDepartment().getId());
        return employeeDTO;
    }

    @Override
    public List<EmployeeDTO> getAllEmployeesByDepartmentId(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("department", "id", departmentId));

        List<Employee> empList = employeeRepository.findByDepartmentId(departmentId);

        return empList.stream().map(employee -> modelMapper.map(employee, EmployeeDTO.class)).toList();
    }

    @Override
    public EmployeeDTO updateEmployee(Long departmentId, Long employeeId, EmployeeDTO employeeDTO) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("department", "id", departmentId));
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("employee", "id", employeeId));
        if (!employee.getDepartment().getId().equals(department.getId())) {
            throw new BadRequestException("Employee does not match with Department Id:" + departmentId);
        }
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());

        Employee updateEmployee = employeeRepository.save(employee);
        EmployeeDTO savedEmployeeDTO = modelMapper.map(updateEmployee, EmployeeDTO.class);
        savedEmployeeDTO.setDepartmentId(departmentId);
        return savedEmployeeDTO;
    }

    @Override
    public String deleteEmployee(Long departmentId, Long employeeId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("department", "id", departmentId));
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("employee", "id", employeeId));
        if (!employee.getDepartment().getId().equals(department.getId())) {
            throw new BadRequestException("Employee does not match with Department Id:" + departmentId);
        }
        employeeRepository.delete(employee);
        return "Employee with the ID:" + employeeId + " deleted successfully...!";
    }

}
