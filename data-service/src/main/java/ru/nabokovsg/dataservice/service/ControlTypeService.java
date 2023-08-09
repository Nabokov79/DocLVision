package ru.nabokovsg.dataservice.service;

import ru.nabokovsg.dataservice.dto.controlType.ControlTypeDto;
import ru.nabokovsg.dataservice.dto.controlType.NewControlTypeDto;
import ru.nabokovsg.dataservice.dto.controlType.UpdateControlTypeDto;
import ru.nabokovsg.dataservice.model.ControlType;

import java.util.List;

public interface ControlTypeService {


    ControlTypeDto save(NewControlTypeDto typeControlDto);

    ControlTypeDto update(UpdateControlTypeDto typeControlDto);

    ControlType get(Long id);

    List<ControlTypeDto> getAll();

    void delete(Long id);
}