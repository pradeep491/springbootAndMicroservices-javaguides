package com.test.service;

import com.test.model.DepartmentDTO;

import java.util.List;

public interface DepartmentService {
    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO);

    public DepartmentDTO getDepartmentById(Long id);

    public List<DepartmentDTO> getAllDepartments();

    public DepartmentDTO updateDepartment(Long departmentId, DepartmentDTO departmentDTO);

    public String deleteDepartment(Long departmentId);
}
