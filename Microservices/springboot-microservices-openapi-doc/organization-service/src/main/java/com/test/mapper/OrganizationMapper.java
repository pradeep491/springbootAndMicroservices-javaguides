package com.test.mapper;

import com.test.dto.OrganizationDTO;
import com.test.entity.Organization;

public class OrganizationMapper {

    public static Organization mapToEntity(OrganizationDTO dto) {
        return new Organization(dto.getId(),
                dto.getOrganizationName(),
                dto.getOrganizationDescription(),
                dto.getOrganizationCode(),
                dto.getCreatedDate());
    }

    public static OrganizationDTO mapToDTO(Organization organization) {
        return new OrganizationDTO(organization.getId(),
                organization.getOrganizationName(),
                organization.getOrganizationDescription(),
                organization.getOrganizationCode(),
                organization.getCreatedDate());
    }
}
