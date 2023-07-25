package ru.nabokovsg.dataservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.license.LicenseDto;
import ru.nabokovsg.dataservice.dto.license.NewLicenseDto;
import ru.nabokovsg.dataservice.dto.license.UpdateLicenseDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mapper.BranchMapper;
import ru.nabokovsg.dataservice.mapper.DepartmentMapper;
import ru.nabokovsg.dataservice.mapper.LicenseMapper;
import ru.nabokovsg.dataservice.mapper.OrganizationMapper;
import ru.nabokovsg.dataservice.model.License;
import ru.nabokovsg.dataservice.repository.LicenseRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LicenseServiceImpl implements LicenseService {

    private final LicenseRepository repository;
    private final LicenseMapper mapper;
    private final OrganizationService organizationService;
    private final OrganizationMapper organizationMapper;
    private final BranchService branchService;
    private final BranchMapper branchMapper;
    private final DepartmentService departmentService;
    private final DepartmentMapper departmentMapper;


    @Override
    public LicenseDto save(NewLicenseDto licenseDto) {
        validateIds(licenseDto.getOrganizationId(), licenseDto.getBranchId(), licenseDto.getDepartmentId());
        License license = mapper.mapToNewLicense(licenseDto);
        license.setOrganization(
                organizationMapper.mapToOrganization(organizationService.get(licenseDto.getOrganizationId()))
        );
        license.setBranch(branchMapper.mapToBranch(branchService.get(licenseDto.getBranchId())));
        license.setDepartment(departmentMapper.mapToDepartment(departmentService.get(licenseDto.getDepartmentId())));
        return mapper.mapToLicenseDto(repository.save(license));
    }

    @Override
    public LicenseDto update(UpdateLicenseDto licenseDto) {
        if (repository.existsById(licenseDto.getId())) {
            validateIds(licenseDto.getOrganizationId(), licenseDto.getBranchId(), licenseDto.getDepartmentId());
            License license = mapper.mapToUpdateLicense(licenseDto);
            license.setOrganization(
                    organizationMapper.mapToOrganization(organizationService.get(licenseDto.getOrganizationId()))
            );
            license.setBranch(branchMapper.mapToBranch(branchService.get(licenseDto.getBranchId())));
            license.setDepartment(departmentMapper.mapToDepartment(departmentService.get(licenseDto.getDepartmentId())));
            return mapper.mapToLicenseDto(repository.save(license));
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
}