package ru.nabokovsg.dataservice.mapper;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.objectsTypeData.ObjectsTypeDataDto;
import ru.nabokovsg.dataservice.model.ObjectsTypeData;

@Mapper(componentModel = "spring")
public interface ObjectsTypeDataMapper {

    ObjectsTypeDataDto mapToObjectsTypeDataDto(ObjectsTypeData objectsTypeData);
}
