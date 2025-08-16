package com.test.service;

import com.test.dto.OrganizationDTO;

public interface OrganizationService {
    OrganizationDTO saveOrganization(OrganizationDTO  dto);
    OrganizationDTO getOrganizationByCode(String code);
}
