package ru.nabokovsg.dataservice.service;

import ru.nabokovsg.dataservice.dto.measuringTool.MeasuringToolDto;
import ru.nabokovsg.dataservice.dto.measuringTool.NewMeasuringToolDto;
import ru.nabokovsg.dataservice.dto.measuringTool.RequestParameters;
import ru.nabokovsg.dataservice.dto.measuringTool.UpdateMeasuringToolDto;

import java.util.List;

public interface MeasuringToolService {

    List<MeasuringToolDto> save(List<NewMeasuringToolDto> newMeasuringToolsDto);

    List<MeasuringToolDto> update(List<UpdateMeasuringToolDto> updateMeasuringToolsDto);

    List<MeasuringToolDto> getAll(RequestParameters parameters);

    void delete(Long id);
}
