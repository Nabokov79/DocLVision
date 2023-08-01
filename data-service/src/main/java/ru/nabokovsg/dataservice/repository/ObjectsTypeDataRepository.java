package ru.nabokovsg.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.dataservice.model.ObjectsType;
import ru.nabokovsg.dataservice.model.ObjectsTypeData;

public interface ObjectsTypeDataRepository extends JpaRepository<ObjectsTypeData, Long> {

    ObjectsTypeData findByObjectsType(ObjectsType objectsType);
}
