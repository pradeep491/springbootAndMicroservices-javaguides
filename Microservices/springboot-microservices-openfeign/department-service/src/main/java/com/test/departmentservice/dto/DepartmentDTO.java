package com.test.departmentservice.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {
    private long id;
    private String departmentName;
    private String departmentDescription;
    private String departmentCode;
}
