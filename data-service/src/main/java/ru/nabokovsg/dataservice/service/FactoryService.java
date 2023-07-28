package ru.nabokovsg.dataservice.service;

import ru.nabokovsg.dataservice.dto.Factory;
import ru.nabokovsg.dataservice.dto.ObjectsIds;
import java.util.List;


public interface FactoryService {

    Factory create(List<ObjectsIds> objects);
}