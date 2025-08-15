package com.test.controller;

import com.test.dto.OrganizationDTO;
import com.test.entity.Organization;
import com.test.mapper.OrganizationMapper;
import com.test.service.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organizations")
public class OrganizationController {

    private OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    //Build save Organization Rest API
    //http://localhost:8083/api/organizations
    @PostMapping
    public ResponseEntity<OrganizationDTO> saveOrganization(@RequestBody OrganizationDTO dto) {
        OrganizationDTO savedOrganization = organizationService.saveOrganization(dto);
        return new ResponseEntity<>(savedOrganization, HttpStatus.CREATED);
    }

    //Build Get Organization by code Rest API
    //http://localhost:8083/api/organizations/IT001
    @GetMapping("/{orgCode}")
    public ResponseEntity<OrganizationDTO> getOrganizationByCode(@PathVariable String orgCode) {
        OrganizationDTO organizationDTO = organizationService.getOrganizationByCode(orgCode);
        return ResponseEntity.ok(organizationDTO);
    }
}
