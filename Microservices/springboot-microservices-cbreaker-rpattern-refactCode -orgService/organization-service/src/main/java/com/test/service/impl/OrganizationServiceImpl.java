package com.test.service.impl;

import com.test.dto.OrganizationDTO;
import com.test.entity.Organization;
import com.test.mapper.OrganizationMapper;
import com.test.repository.OrganizationRepository;
import com.test.service.OrganizationService;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl implements
        OrganizationService {

    private OrganizationRepository organizationRepository;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Override
    public OrganizationDTO saveOrganization(OrganizationDTO dto) {
        Organization organization = OrganizationMapper.mapToEntity(dto);
        Organization savedOrganization = organizationRepository.save(organization);
        return OrganizationMapper.mapToDTO(savedOrganization);
    }

    @Override
    public OrganizationDTO getOrganizationByCode(String code) {
        Organization organization = organizationRepository.findByOrganizationCode(code);
        return OrganizationMapper.mapToDTO(organization);
    }

}
