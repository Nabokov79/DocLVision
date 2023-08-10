package ru.nabokovsg.dataservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.model.Builder;
import ru.nabokovsg.dataservice.model.ObjectsIds;
import ru.nabokovsg.dataservice.dto.license.LicenseDto;
import ru.nabokovsg.dataservice.dto.license.NewLicenseDto;
import ru.nabokovsg.dataservice.dto.license.UpdateLicenseDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mapper.*;
import ru.nabokovsg.dataservice.model.BuilderType;
import ru.nabokovsg.dataservice.model.License;
import ru.nabokovsg.dataservice.repository.LicenseRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LicenseServiceImpl implements LicenseService {

    private final LicenseRepository repository;
    private final LicenseMapper mapper;
    private final BranchService branchService;
    private final BranchMapper branchMapper;
    private final DepartmentService departmentService;
    private final DepartmentMapper departmentMapper;
    private final BuilderFactoryService factoryService;
    private final ObjectsIdsMapper objectsIdsMapper;


    @Override
    public LicenseDto save(NewLicenseDto licenseDto) {
        validateIds(licenseDto.getOrganizationId(), licenseDto.getBranchId(), licenseDto.getDepartmentId());
        return mapper.mapToLicenseDto(repository.save(set(mapper.mapToNewLicense(licenseDto)
                                                        , objectsIdsMapper.mapFromNewLicense(licenseDto))));
    }

    @Override
    public LicenseDto update(UpdateLicenseDto licenseDto) {
        if (repository.existsById(licenseDto.getId())) {
            return mapper.mapToLicenseDto(repository.save(set(mapper.mapToUpdateLicense(licenseDto)
                                                           , objectsIdsMapper.mapFromUpdateLicense(licenseDto))));
        }
        throw new NotFoundException(String.format("license with id=%s not found for update", licenseDto.getId()));
    }

    @Override
    public LicenseDto get(Long id) {
        return mapper.mapToLicenseDto(
                repository.findById(id).orElseThrow(() -> new NotFoundException(
                                                                String.format("license with id=%s not found", id))));
    }

    @Override
    public List<LicenseDto> getAll() {
        return mapper.mapToLicensesDto(repository.findAll());
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("License with id=%s not found for delete.", id));
    }

    private void validateIds(Long organizationId, Long branchId, Long departmentId) {
        if (organizationId == null || organizationId < 0) {
            throw new NotFoundException(String.format("organization id=%s is not negative", organizationId));
        }
        if (branchId == null || branchId < 0) {
            throw new NotFoundException(String.format("branch id=%s is not negative", branchId));
        }
        if (departmentId == null || departmentId < 0) {
            throw new NotFoundException(String.format("department id=%s is not or negative", departmentId));
        }
    }
    private License set(License license, ObjectsIds ids) {
        Builder builder = factoryService.getBuilder(List.of(ids), BuilderType.LICENSE);
        license.setOrganization(builder.getOrganizations().get(ids.getOrganizationId()));
        license.setIssuedLicense(builder.getOrganizations().get(ids.getIssuedLicenseId()));
        license.setBranch(branchMapper.mapToBranch(branchService.get(ids.getBranchId())));
        license.setDepartment(departmentMapper.mapToDepartment(departmentService.get(ids.getDepartmentId())));
        return license;
    }
}