package ru.nabokovsg.dataservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.organization.NewOrganizationDto;
import ru.nabokovsg.dataservice.dto.organization.OrganizationDto;
import ru.nabokovsg.dataservice.dto.organization.ShortOrganizationDto;
import ru.nabokovsg.dataservice.dto.organization.UpdateOrganizationDto;
import ru.nabokovsg.dataservice.exceptions.BadRequestException;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mapper.OrganizationMapper;
import ru.nabokovsg.dataservice.model.Organization;
import ru.nabokovsg.dataservice.repository.OrganizationRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository repository;
    private final OrganizationMapper mapper;
    private final RequisitesService requisitesService;

    @Override
    public OrganizationDto save(NewOrganizationDto organizationDto) {
        if (repository.existsByOrganization(organizationDto.getOrganization())) {
            throw new BadRequestException(
                    String.format("organization=%s found", organizationDto.getOrganization())
            );
        }
        Organization organization = mapper.mapToNewOrganization(organizationDto);
        organization.setRequisites(requisitesService.save(organizationDto.getRequisites()));
        return mapper.mapToOrganizationDto(repository.save(organization));
    }

    @Override
    public OrganizationDto update(UpdateOrganizationDto organizationDto) {
        if (repository.existsById(organizationDto.getId())) {
            Organization organization = mapper.mapToUpdateOrganization(organizationDto);
            organization.setRequisites(requisitesService.update(organizationDto.getRequisites()));
            return mapper.mapToOrganizationDto(repository.save(mapper.mapToUpdateOrganization(organizationDto)));
        }
        throw new NotFoundException(
                String.format("organization=%s not found for update.", organizationDto.getOrganization()));
    }

    @Override
    public OrganizationDto get(Long id) {
        return mapper.mapToOrganizationDto(repository.findById(id).orElseThrow(() -> new NotFoundException(
                                                   String.format("Organization with id=%s not found for license",id))));
    }

    @Override
    public List<ShortOrganizationDto> getAll() {
        return mapper.mapToShortOrganizationDto(repository.findAllOrganization());
    }

    @Override
    public List<Organization> getAllByIds(List<Long> ids) {
        if (ids.isEmpty()) {
            throw new NotFoundException(String.format("Organization with ids=%s not found", ids));
        }
        return repository.findAllById(ids);
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
       throw new NotFoundException(String.format("Organization with id=%s not found for delete.", id));
    }
}