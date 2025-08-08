package com.test.departmentservice.service.impl;

import com.test.departmentservice.dto.DepartmentDTO;
import com.test.departmentservice.entities.Department;
import com.test.departmentservice.repos.DepartmentRepository;
import com.test.departmentservice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository repo;

    public DepartmentServiceImpl(DepartmentRepository repo) {
        this.repo = repo;
    }

    @Override
    public DepartmentDTO saveDepartment(DepartmentDTO departmentdto) {
        //convert DepartmentDTO to Department JPA Entity
        Department department = new Department(departmentdto.getId(),
                departmentdto.getDepartmentName(),
                departmentdto.getDepartmentDescription(),
                departmentdto.getDepartmentCode());
        Department savedDepartment = repo.save(department);
        DepartmentDTO dto = new DepartmentDTO(savedDepartment.getId(),
                savedDepartment.getDepartmentName(),
                savedDepartment.getDepartmentDescription(),
                savedDepartment.getDepartmentCode());


        return dto;
    }

    @Override
    public DepartmentDTO getDepartmentByDeptCode(String code) {
        Department dept = repo.findByDepartmentCode(code);

        return new DepartmentDTO(dept.getId(),
                dept.getDepartmentName(),
                dept.getDepartmentDescription(),
                dept.getDepartmentCode());
    }

}
