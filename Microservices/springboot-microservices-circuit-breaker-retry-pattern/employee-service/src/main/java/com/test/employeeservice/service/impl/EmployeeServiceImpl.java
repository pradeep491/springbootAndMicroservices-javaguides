package com.test.employeeservice.service.impl;

import com.test.employeeservice.dto.APIResponseDTO;
import com.test.employeeservice.dto.DepartmentDTO;
import com.test.employeeservice.dto.EmployeeDTO;
import com.test.employeeservice.entities.Employee;
import com.test.employeeservice.repos.EmployeeRepository;
import com.test.employeeservice.service.APIClient;
import com.test.employeeservice.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
//@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private final EmployeeRepository repo;
    //private APIClient apiClient;
    private WebClient webClient;
    public EmployeeServiceImpl(EmployeeRepository repo,WebClient webClient) {
        this.repo = repo;
        this.webClient = webClient;
    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO dto) {
        Employee e = new Employee(dto.getId(), dto.getFirstName(),
                dto.getLastName(), dto.getEmail(), dto.getDepartmentCode());
        Employee savedEmployee = repo.save(e);

        EmployeeDTO dto1 = new EmployeeDTO(savedEmployee.getId(),
                savedEmployee.getFirstName(),
                savedEmployee.getLastName(),
                savedEmployee.getEmail(),
                savedEmployee.getDepartmentCode());
        return dto1;
    }

    //@CircuitBreaker(name="${spring.application.name}",fallbackMethod = "getDefaultDepartment")
    @Retry(name="${spring.application.name}",fallbackMethod = "getDefaultDepartment")
    @Override
    public APIResponseDTO getEmployeeByEmpid(Long empid) {
        logger.info("inside getEmployeeByEmpid()");
        Employee e = repo.findById(empid).get();
        DepartmentDTO departmentDTO = webClient.get()
                .uri("http://localhost:8080/api/departments/" + e.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDTO.class)
                .block();

        EmployeeDTO dto = new EmployeeDTO(e.getId(), e.getFirstName(), e.getLastName(), e.getEmail(),e.getDepartmentCode());

        APIResponseDTO responseDTO = new APIResponseDTO();
        responseDTO.setDepartment(departmentDTO);
        responseDTO.setEmployee(dto);
        return responseDTO;
    }
    public APIResponseDTO getDefaultDepartment(Long empid,Exception exception) {
        logger.info("inside getDefaultDepartment() getDefaultDepartment");
        Employee e = repo.findById(empid).get();
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setDepartmentName("R&D Development");
        departmentDTO.setDepartmentCode("RD001");
        departmentDTO.setDepartmentDescription("Research & Development");
        EmployeeDTO dto = new EmployeeDTO(e.getId(), e.getFirstName(), e.getLastName(), e.getEmail(),e.getDepartmentCode());

        APIResponseDTO responseDTO = new APIResponseDTO();
        responseDTO.setDepartment(departmentDTO);
        responseDTO.setEmployee(dto);
        return responseDTO;
    }

}
