package com.test.repos;

import com.test.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    public List<Employee> findByDepartmentId(Long departmentId);
}
