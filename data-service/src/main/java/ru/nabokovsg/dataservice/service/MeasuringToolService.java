package ru.nabokovsg.dataservice.service;

import ru.nabokovsg.dataservice.dto.measuringTool.MeasuringToolDto;
import ru.nabokovsg.dataservice.dto.measuringTool.NewMeasuringToolDto;
import ru.nabokovsg.dataservice.dto.measuringTool.RequestParameters;
import ru.nabokovsg.dataservice.dto.measuringTool.UpdateMeasuringToolDto;

import java.util.List;

public interface MeasuringToolService {

    MeasuringToolDto save(NewMeasuringToolDto newMeasuringTool);

    MeasuringToolDto update(UpdateMeasuringToolDto updateMeasuringTool);

    List<MeasuringToolDto> getAll(RequestParameters parameters);

    void delete(Long id);
}
