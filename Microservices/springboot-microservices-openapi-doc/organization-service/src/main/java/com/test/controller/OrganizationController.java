package com.test.controller;

import com.test.dto.OrganizationDTO;
import com.test.entity.Organization;
import com.test.mapper.OrganizationMapper;
import com.test.service.OrganizationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Tag(
        name = "Organization Controller",
        description = "Organization Controller exposes REST API's for Organization Service"
)
@RestController
@RequestMapping("/api/organizations")
public class OrganizationController {

    private OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @Operation(
            summary = "Summary Organization REST API",
            description = "save Organization REST API is used to save Organization Object in a DB"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 Created"
    )
    //Build save Organization Rest API
    //http://localhost:8083/api/organizations
    @PostMapping
    public ResponseEntity<OrganizationDTO> saveOrganization(@RequestBody OrganizationDTO dto) {
        OrganizationDTO savedOrganization = organizationService.saveOrganization(dto);
        return new ResponseEntity<>(savedOrganization, HttpStatus.CREATED);
    }
    @Operation(
            summary = "Summary Organization REST API",
            description = "save Organization REST API is used to get Organization Object from the DB"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    //Build Get Organization by code Rest API
    //http://localhost:8083/api/organizations/IT001
    @GetMapping("/{orgCode}")
    public ResponseEntity<OrganizationDTO> getOrganizationByCode(@PathVariable String orgCode) {
        OrganizationDTO organizationDTO = organizationService.getOrganizationByCode(orgCode);
        return ResponseEntity.ok(organizationDTO);
    }
}
