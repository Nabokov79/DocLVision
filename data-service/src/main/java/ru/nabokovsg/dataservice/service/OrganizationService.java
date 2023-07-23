package ru.nabokovsg.dataservice.service;

import ru.nabokovsg.dataservice.dto.organization.NewOrganizationDto;
import ru.nabokovsg.dataservice.dto.organization.OrganizationDto;
import ru.nabokovsg.dataservice.dto.organization.ShortOrganizationDto;
import ru.nabokovsg.dataservice.dto.organization.UpdateOrganizationDto;

import java.util.List;

public interface OrganizationService {

    OrganizationDto save(NewOrganizationDto organizationDto);

    OrganizationDto update(UpdateOrganizationDto organizationDto);

    OrganizationDto get(Long id);

    List<ShortOrganizationDto> getAll();

    void delete(Long id);
}