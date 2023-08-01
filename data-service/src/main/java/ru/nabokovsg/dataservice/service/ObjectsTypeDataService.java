package ru.nabokovsg.dataservice.service;

import ru.nabokovsg.dataservice.dto.objectsTypeData.ObjectsTypeDataDto;
import java.util.List;

public interface ObjectsTypeDataService {

    ObjectsTypeDataDto get(Long id);

    ObjectsTypeDataDto addElements(Long objectsTypeId, List<Long> elementIds);

    ObjectsTypeDataDto updateElements(Long id, List<Long> elementIds);

    ObjectsTypeDataDto addDefects(Long objectsTypeId,List<Long> defectIds);

    ObjectsTypeDataDto updateDefects(Long id, List<Long> defectIds);

    ObjectsTypeDataDto addDocumentation(Long objectsTypeId,List<Long> documentationIds);

    ObjectsTypeDataDto updateDocumentation(Long id, List<Long> documentationIds);

    ObjectsTypeDataDto addObjectPassportDataTemplate(Long objectsTypeId,List<Long> templatesIds);

    ObjectsTypeDataDto updateObjectPassportDataTemplate(Long id, List<Long> templatesIds);
}