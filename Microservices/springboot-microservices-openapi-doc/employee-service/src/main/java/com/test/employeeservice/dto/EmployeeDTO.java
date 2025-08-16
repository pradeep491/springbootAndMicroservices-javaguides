package com.test.employeeservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "EmployeeDTO Model Information")
public class EmployeeDTO {
    private Long id;
    @Schema(description = "Employee First Name")
    private String firstName;
    @Schema(description = "Employee Last Name")
    private String lastName;
    @Schema(description = "Employee Email Id")
    private String email;
    @Schema(description = "Employee Department Code")
    private String departmentCode;
    @Schema(description = "Employee Organization Code")
    private String organizationCode;
}
