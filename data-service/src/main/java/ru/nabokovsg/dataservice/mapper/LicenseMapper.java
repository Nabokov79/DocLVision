package ru.nabokovsg.dataservice.mapper;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.license.LicenseDto;
import ru.nabokovsg.dataservice.dto.license.NewLicenseDto;
import ru.nabokovsg.dataservice.dto.license.UpdateLicenseDto;
import ru.nabokovsg.dataservice.model.License;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LicenseMapper {

    License mapToNewLicense(NewLicenseDto licenseDto);

    LicenseDto mapToLicenseDto(License license);

    License mapToUpdateLicense(UpdateLicenseDto licenseDto);

    List<LicenseDto> mapToLicensesDto(List<License> licenses);
}