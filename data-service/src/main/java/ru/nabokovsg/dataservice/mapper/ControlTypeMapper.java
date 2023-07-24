package ru.nabokovsg.dataservice.mapper;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.controlType.ControlTypeDto;
import ru.nabokovsg.dataservice.dto.controlType.NewControlTypeDto;
import ru.nabokovsg.dataservice.dto.controlType.UpdateControlTypeDto;
import ru.nabokovsg.dataservice.model.ControlType;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ControlTypeMapper {

    ControlType mapToNewControlType(NewControlTypeDto typeControlDto);

    ControlTypeDto mapToControlTypeDto(ControlType typeControl);

    ControlType mapToUpdateControlType(UpdateControlTypeDto typeControlDto);

    List<ControlTypeDto> mapToControlTypesDto(List<ControlType> typeControls);
}