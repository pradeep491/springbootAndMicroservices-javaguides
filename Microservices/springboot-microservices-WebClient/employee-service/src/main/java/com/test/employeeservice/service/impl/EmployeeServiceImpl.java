package com.test.employeeservice.service.impl;

import com.test.employeeservice.dto.APIResponseDTO;
import com.test.employeeservice.dto.DepartmentDTO;
import com.test.employeeservice.dto.EmployeeDTO;
import com.test.employeeservice.entities.Employee;
import com.test.employeeservice.repos.EmployeeRepository;
import com.test.employeeservice.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
//@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repo;
    //private RestTemplate restTemplate;
    private WebClient webClient;

    public EmployeeServiceImpl(EmployeeRepository repo,
                               WebClient webClient) {
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

    @Override
    public APIResponseDTO getEmployeeByEmpid(Long empid) {
        Employee e = repo.findById(empid).get();
        /*ResponseEntity<DepartmentDTO>  responseEntity= restTemplate.getForEntity("http://localhost:8080/api/departments/"+e.getDepartmentCode(), DepartmentDTO.class);
        DepartmentDTO departmentDTO = responseEntity.getBody();*/
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


}
