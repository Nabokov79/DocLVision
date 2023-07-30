package ru.nabokovsg.dataservice.mapper;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.element.ElementDto;
import ru.nabokovsg.dataservice.dto.element.NewElementDto;
import ru.nabokovsg.dataservice.dto.element.UpdateElementDto;
import ru.nabokovsg.dataservice.model.Element;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ElementMapper {

    Element mapToNewElement(NewElementDto elementDto);

    Element mapToUpdateElements(UpdateElementDto elementDto);

    List<ElementDto> mapToElementsDto(List<Element> elements);
}