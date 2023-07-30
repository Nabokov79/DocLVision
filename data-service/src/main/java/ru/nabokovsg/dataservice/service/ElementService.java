package ru.nabokovsg.dataservice.service;

import ru.nabokovsg.dataservice.dto.element.ElementDto;
import ru.nabokovsg.dataservice.dto.element.NewElementDto;
import ru.nabokovsg.dataservice.dto.element.UpdateElementDto;

import java.util.List;

public interface ElementService {

    List<ElementDto> save(List<NewElementDto> elementsDto);

    List<ElementDto> update(List<UpdateElementDto> elementsDto);
}