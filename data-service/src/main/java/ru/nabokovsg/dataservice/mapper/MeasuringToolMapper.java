package ru.nabokovsg.dataservice.mapper;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.measuringTool.MeasuringToolDto;
import ru.nabokovsg.dataservice.dto.measuringTool.MeasuringToolIds;
import ru.nabokovsg.dataservice.dto.measuringTool.NewMeasuringToolDto;
import ru.nabokovsg.dataservice.dto.measuringTool.UpdateMeasuringToolDto;
import ru.nabokovsg.dataservice.model.MeasuringTool;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MeasuringToolMapper {

    MeasuringTool mapToNewMeasuringTool(NewMeasuringToolDto newMeasuringToolDto);

    MeasuringToolDto mapToMeasuringToolDto(MeasuringTool measuringTools);

    MeasuringTool mapToUpdateMeasuringTool(UpdateMeasuringToolDto measuringToolDto);

    List<MeasuringToolDto> mapToMeasuringToolsDto(List<MeasuringTool> newMeasuringTools);

    MeasuringToolIds mapToNeMeasuringToolValue(NewMeasuringToolDto newMeasuringToolDto);
    MeasuringToolIds mapToUpdateMeasuringToolValue(UpdateMeasuringToolDto measuringToolDto);
}
