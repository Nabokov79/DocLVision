package ru.nabokovsg.dataservice.service;

import ru.nabokovsg.dataservice.dto.license.LicenseDto;
import ru.nabokovsg.dataservice.dto.license.NewLicenseDto;
import ru.nabokovsg.dataservice.dto.license.UpdateLicenseDto;

import java.util.List;

public interface LicenseService {

    LicenseDto save(NewLicenseDto licenseDto);

    LicenseDto update(UpdateLicenseDto licenseDto);

    LicenseDto get(Long id);

    List<LicenseDto> getAll();

    void delete(Long id);
}