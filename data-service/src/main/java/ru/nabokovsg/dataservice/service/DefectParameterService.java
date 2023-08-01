package ru.nabokovsg.dataservice.service;

import ru.nabokovsg.dataservice.dto.defect.*;
import ru.nabokovsg.dataservice.model.DefectParameter;

import java.util.List;
import java.util.Set;

public interface DefectParameterService {

    Set<DefectParameter> save(List<NewDefectParameterDto> parametersDto);

    Set<DefectParameter> update(List<UpdateDefectParameterDto> parametersDto);
}