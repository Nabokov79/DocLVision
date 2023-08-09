package ru.nabokovsg.dataservice.mapper;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.ObjectsIds;
import ru.nabokovsg.dataservice.dto.certificate.NewCertificateDto;
import ru.nabokovsg.dataservice.dto.certificate.UpdateCertificateDto;
import ru.nabokovsg.dataservice.dto.dataPassportOfObject.NewDataPassportOfObjectDto;
import ru.nabokovsg.dataservice.dto.dataPassportOfObject.UpdateDataPassportOfObjectDto;
import ru.nabokovsg.dataservice.dto.license.NewLicenseDto;
import ru.nabokovsg.dataservice.dto.license.UpdateLicenseDto;
import ru.nabokovsg.dataservice.dto.measuringTool.NewMeasuringToolDto;
import ru.nabokovsg.dataservice.dto.measuringTool.UpdateMeasuringToolDto;
import ru.nabokovsg.dataservice.dto.object.NewObjectDto;
import ru.nabokovsg.dataservice.dto.object.UpdateObjectDto;
import ru.nabokovsg.dataservice.dto.repair.NewRepairDto;
import ru.nabokovsg.dataservice.dto.repair.UpdateRepairDto;
import ru.nabokovsg.dataservice.dto.survey.NewSurveyDto;
import ru.nabokovsg.dataservice.dto.survey.UpdateSurveyDto;

@Mapper(componentModel = "spring")
public interface ObjectsIdsMapper {

    ObjectsIds mapFromNewCertificateDto(NewCertificateDto certificateDto);

    ObjectsIds mapFromUpdateCertificateDto(UpdateCertificateDto certificateDto);

    ObjectsIds mapFromNewMeasuringToolDto(NewMeasuringToolDto measuringToolDto);

    ObjectsIds mapFromUpdateMeasuringToolDto(UpdateMeasuringToolDto measuringToolDto);

    ObjectsIds mapFromNewObjectDto(NewObjectDto objectDto);

    ObjectsIds mapFromUpdateObjectDto(UpdateObjectDto objectDto);

    ObjectsIds mapFromNewLicense(NewLicenseDto objectDto);

    ObjectsIds mapFromUpdateLicense(UpdateLicenseDto objectDto);

    ObjectsIds mapFromNewSurveyDto(NewSurveyDto surveyDto);

    ObjectsIds mapFromUpdateSurveyDto(UpdateSurveyDto surveyDto);

    ObjectsIds mapFromNewRepairDto(NewRepairDto repairDto);

    ObjectsIds mapFromUpdateRepairDto(UpdateRepairDto repairDto);

    ObjectsIds mapFromNewDataPassportOfObjectDto(NewDataPassportOfObjectDto repairDto);

    ObjectsIds mapFromUpdateDataPassportOfObjectDto(UpdateDataPassportOfObjectDto repairDto);
}