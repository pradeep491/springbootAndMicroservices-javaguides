package com.test.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Schema(description = "OrganizationDTO Model Information")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDTO {
    private Long id;
    @Schema(
            description = "organization Name"
    )
    private String organizationName;
    @Schema(
            description = "organization Description"
    )
    private String organizationDescription;
    @Schema(
            description = "organization Code"
    )
    private String organizationCode;
    @Schema(
            description = "organization Created Date"
    )
    private LocalDateTime createdDate;
}
