package ru.nabokovsg.dataservice.service;

import ru.nabokovsg.dataservice.dto.element.ElementDto;
import ru.nabokovsg.dataservice.dto.element.NewElementDto;
import ru.nabokovsg.dataservice.dto.element.UpdateElementDto;
import ru.nabokovsg.dataservice.model.Element;

import java.util.List;
import java.util.Set;

public interface ElementService {

    List<ElementDto> save(List<NewElementDto> elementsDto);

    List<ElementDto> update(List<UpdateElementDto> elementsDto);

    Set<Element> getAllByIds(List<Long> elementIds);
}