package ru.nabokovsg.dataservice.service;

import ru.nabokovsg.dataservice.dto.Builder;
import ru.nabokovsg.dataservice.dto.ObjectsIds;
import ru.nabokovsg.dataservice.model.BuilderType;

import java.util.List;

public interface BuilderFactoryService {

    Builder getBuilder(List<ObjectsIds> ids, BuilderType type);
}