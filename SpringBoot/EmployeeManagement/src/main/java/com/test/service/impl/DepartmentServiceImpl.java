package com.test.service.impl;

import com.test.entity.Department;
import com.test.exception.ResourceNotFoundException;
import com.test.model.DepartmentDTO;
import com.test.repos.DepartmentRepository;
import com.test.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final ModelMapper modelMapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        Department department = modelMapper.map(departmentDTO, Department.class);
        Department savedDepartment = departmentRepository.save(department);
        return modelMapper.map(savedDepartment, DepartmentDTO.class);
    }

    @Override
    public DepartmentDTO getDepartmentById(Long id) {
        Department savedDepartment = departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("department", "id", id));
        return modelMapper.map(savedDepartment, DepartmentDTO.class);
    }

    @Override
    public List<DepartmentDTO> getAllDepartments() {
        List<Department> departmentsList = departmentRepository.findAll();
        return departmentsList.stream().map(department ->
                modelMapper.map(department, DepartmentDTO.class)).toList();
    }

    @Override
    public DepartmentDTO updateDepartment(Long departmentId, DepartmentDTO departmentDTO) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("department", "id", departmentId));
        department.setDepartmentName(departmentDTO.getDepartmentName());
        department.setDepartmentDescription(departmentDTO.getDepartmentDescription());

        Department updatedDepartment = departmentRepository.save(department);
        return modelMapper.map(updatedDepartment, DepartmentDTO.class);
    }

    @Override
    public String deleteDepartment(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("department", "id", departmentId));
        departmentRepository.delete(department);
        return "Department with the Id:" + departmentId + " deleted successfully...!";
    }
}
