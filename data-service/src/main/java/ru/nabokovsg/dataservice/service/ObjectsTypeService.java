package ru.nabokovsg.dataservice.service;

import ru.nabokovsg.dataservice.dto.objectsType.NewObjectsTypeDto;
import ru.nabokovsg.dataservice.dto.objectsType.ObjectsTypeDto;
import ru.nabokovsg.dataservice.dto.objectsType.UpdateObjectsTypeDto;
import ru.nabokovsg.dataservice.model.ObjectsType;

import java.util.List;

public interface ObjectsTypeService {

    List<ObjectsTypeDto> save(List<NewObjectsTypeDto> objectsTypeDto);

    List<ObjectsTypeDto> update(List<UpdateObjectsTypeDto> objectsTypeDto);

    ObjectsType get(Long id);

    void delete(Long id);
}