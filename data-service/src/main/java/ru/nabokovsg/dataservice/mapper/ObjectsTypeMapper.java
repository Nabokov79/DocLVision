package ru.nabokovsg.dataservice.mapper;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.objectsType.NewObjectsTypeDto;
import ru.nabokovsg.dataservice.dto.objectsType.ObjectsTypeDto;
import ru.nabokovsg.dataservice.dto.objectsType.UpdateObjectsTypeDto;
import ru.nabokovsg.dataservice.model.ObjectsType;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ObjectsTypeMapper {

    List<ObjectsType> mapToNewObjectsType(List<NewObjectsTypeDto> objectsTypesDto);

    List<ObjectsType> mapToUpdateObjectsType(List<UpdateObjectsTypeDto> objectsTypesDto);

    List<ObjectsTypeDto> mapToObjectsTypeDto(List<ObjectsType> objectsTypes);
}