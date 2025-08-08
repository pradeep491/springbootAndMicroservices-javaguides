package com.test.departmentservice.service;

import com.test.departmentservice.dto.DepartmentDTO;

public interface DepartmentService {

    public DepartmentDTO saveDepartment(DepartmentDTO departmentdto);
    public DepartmentDTO getDepartmentByDeptCode(String code);
}
