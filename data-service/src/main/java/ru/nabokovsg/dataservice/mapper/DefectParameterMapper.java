package ru.nabokovsg.dataservice.mapper;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.defect.NewDefectParameterDto;
import ru.nabokovsg.dataservice.dto.defect.UpdateDefectParameterDto;
import ru.nabokovsg.dataservice.model.DefectParameter;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DefectParameterMapper {

    List<DefectParameter> mapToNewDefectParameter(List<NewDefectParameterDto> parametersDto);

    List<DefectParameter> mapToUpdateDefectParameter(List<UpdateDefectParameterDto> parametersDto);
}