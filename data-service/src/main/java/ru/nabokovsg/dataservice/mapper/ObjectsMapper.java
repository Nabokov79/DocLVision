package ru.nabokovsg.dataservice.mapper;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.object.NewObjectDto;
import ru.nabokovsg.dataservice.dto.object.ObjectDto;
import ru.nabokovsg.dataservice.dto.object.UpdateObjectDto;
import ru.nabokovsg.dataservice.model.Objects;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ObjectsMapper {

    Objects mapToNewObjects(NewObjectDto objectDto);

    Objects mapToUpdateObjects(UpdateObjectDto objectDto);

    List<ObjectDto> mapToObjectDto(List<Objects> objects);
}