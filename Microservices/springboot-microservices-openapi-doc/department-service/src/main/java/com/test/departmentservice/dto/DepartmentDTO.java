package com.test.departmentservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DepartmentDTO Model Information")
public class DepartmentDTO {
    private long id;
    @Schema(
            description = "Department Name"
    )
    private String departmentName;
    @Schema(
            description = "Department Description"
    )
    private String departmentDescription;
    @Schema(
            description = "Department Code"
    )
    private String departmentCode;
}
