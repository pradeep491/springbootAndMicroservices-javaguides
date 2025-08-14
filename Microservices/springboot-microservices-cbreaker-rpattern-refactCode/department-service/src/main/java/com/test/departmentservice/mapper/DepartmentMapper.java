package com.test.departmentservice.mapper;

import com.test.departmentservice.dto.DepartmentDTO;
import com.test.departmentservice.entities.Department;

public class DepartmentMapper {
    public static DepartmentDTO maptoDepartmentDTO(Department department) {
        DepartmentDTO departmentDTO = new DepartmentDTO(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription(),
                department.getDepartmentCode());
        return departmentDTO;
    }
    public static Department maptoDepartment(DepartmentDTO departmentdto) {
        Department department = new Department(
                departmentdto.getId(),
                departmentdto.getDepartmentName(),
                departmentdto.getDepartmentDescription(),
                departmentdto.getDepartmentCode());
        return department;
    }
}
