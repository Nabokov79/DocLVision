package ru.nabokovsg.dataservice.service;

import ru.nabokovsg.dataservice.dto.Builder;
import ru.nabokovsg.dataservice.dto.ObjectsIds;
import ru.nabokovsg.dataservice.model.*;

import java.util.List;

public interface BuilderService {

    Builder getBuilder(List<ObjectsIds> ids, BuilderType type);
}