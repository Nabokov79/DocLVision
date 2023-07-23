package ru.nabokovsg.dataservice.mapper;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.organization.NewOrganizationDto;
import ru.nabokovsg.dataservice.dto.organization.OrganizationDto;
import ru.nabokovsg.dataservice.dto.organization.ShortOrganizationDto;
import ru.nabokovsg.dataservice.dto.organization.UpdateOrganizationDto;
import ru.nabokovsg.dataservice.model.Organization;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface OrganizationMapper {

    Organization mapToNewOrganization(NewOrganizationDto organizationDto);

    Organization mapToUpdateOrganization(UpdateOrganizationDto organizationDto);

    OrganizationDto mapToOrganizationDto(Organization organization);

    Organization mapToOrganization(OrganizationDto organizationDto);

    List<ShortOrganizationDto> mapToShortOrganizationDto(Set<Organization> organization);
}