package ru.nabokovsg.dataservice.mapper;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.ObjectsIds;
import ru.nabokovsg.dataservice.dto.certificate.NewCertificateDto;
import ru.nabokovsg.dataservice.dto.certificate.UpdateCertificateDto;
import ru.nabokovsg.dataservice.dto.measuringTool.NewMeasuringToolDto;
import ru.nabokovsg.dataservice.dto.measuringTool.UpdateMeasuringToolDto;

@Mapper(componentModel = "spring")
public interface IdsMapper {

    ObjectsIds mapFromNewCertificateDto(NewCertificateDto certificateDto);

    ObjectsIds mapFromUpdateCertificateDto(UpdateCertificateDto certificateDto);

    ObjectsIds mapFromNewMeasuringToolDto(NewMeasuringToolDto measuringToolDto);

    ObjectsIds mapFromUpdateMeasuringToolDto(UpdateMeasuringToolDto measuringToolDto);
}