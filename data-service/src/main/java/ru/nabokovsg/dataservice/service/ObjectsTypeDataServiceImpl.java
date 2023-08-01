package ru.nabokovsg.dataservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.objectsTypeData.ObjectsTypeDataDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mapper.ObjectsTypeDataMapper;
import ru.nabokovsg.dataservice.model.*;
import ru.nabokovsg.dataservice.repository.ObjectsTypeDataRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ObjectsTypeDataServiceImpl implements ObjectsTypeDataService {

    private final ObjectsTypeDataRepository repository;
    private final ObjectsTypeDataMapper mapper;
    private final ElementService elementService;
    private final ObjectsTypeService objectsTypeService;
    private final DefectsService defectsService;
    private final DocumentationService documentationService;
    private final ObjectPassportDataTemplateService templateService;

    @Override
    public ObjectsTypeDataDto get(Long id) {
        return mapper.mapToObjectsTypeDataDto(getById(id));
    }

    @Override
    public ObjectsTypeDataDto addElements(Long objectsTypeId, List<Long> elementIds) {
        ObjectsType objectsType = objectsTypeService.get(objectsTypeId);
        ObjectsTypeData objectsTypeData = repository.findByObjectsType(objectsType);
        Set<Element> elements = elementService.getAllByIds(elementIds);
        if (objectsTypeData == null) {
            objectsTypeData = new ObjectsTypeData();
            objectsTypeData.setObjectsType(objectsType);
            objectsTypeData.setElements(elements);
            return mapper.mapToObjectsTypeDataDto(repository.save(objectsTypeData));
        }
        elements.addAll(objectsTypeData.getElements());
        objectsTypeData.setElements(elements);
        return mapper.mapToObjectsTypeDataDto(repository.save(objectsTypeData));
    }

    @Override
    public ObjectsTypeDataDto updateElements(Long id, List<Long> elementIds) {
        ObjectsTypeData objectsTypeData = getById(id);
        Map<Long, Element> elements = objectsTypeData.getElements().stream()
                                                                   .collect(Collectors.toMap(Element::getId, e -> e));
        for (Long elementId : elementIds) {
            elements.remove(elementId);
        }
        objectsTypeData.setElements(new HashSet<>(elements.values()));
        return mapper.mapToObjectsTypeDataDto(repository.save(objectsTypeData));
    }

    @Override
    public ObjectsTypeDataDto addDefects(Long objectsTypeId, List<Long> defectIds) {
        ObjectsType objectsType = objectsTypeService.get(objectsTypeId);
        ObjectsTypeData objectsTypeData = repository.findByObjectsType(objectsType);
        Set<Defect> defects = defectsService.getAllByIds(defectIds);
        if (objectsTypeData == null) {
            objectsTypeData = new ObjectsTypeData();
            objectsTypeData.setObjectsType(objectsType);
            objectsTypeData.setDefects(defects);
            return mapper.mapToObjectsTypeDataDto(repository.save(objectsTypeData));
        }
        defects.addAll(objectsTypeData.getDefects());
        objectsTypeData.setDefects(defects);
        return mapper.mapToObjectsTypeDataDto(repository.save(objectsTypeData));
    }

    @Override
    public ObjectsTypeDataDto updateDefects(Long id, List<Long> defectIds) {
        ObjectsTypeData objectsTypeData = getById(id);
        Map<Long, Defect> defects = objectsTypeData.getDefects().stream()
                                                                .collect(Collectors.toMap(Defect::getId, d -> d));
        for (Long defectId : defectIds) {
            defects.remove(defectId);
        }
        objectsTypeData.setDefects(new HashSet<>(defects.values()));
        return mapper.mapToObjectsTypeDataDto(repository.save(objectsTypeData));
    }

    @Override
    public ObjectsTypeDataDto addDocumentation(Long objectsTypeId, List<Long> documentationIds) {
        ObjectsType objectsType = objectsTypeService.get(objectsTypeId);
        ObjectsTypeData objectsTypeData = repository.findByObjectsType(objectsType);
        Set<Documentation> documentation = documentationService.getAllByIds(documentationIds);
        if (objectsTypeData == null) {
            objectsTypeData = new ObjectsTypeData();
            objectsTypeData.setObjectsType(objectsType);
            objectsTypeData.setDocumentations(documentation);
            return mapper.mapToObjectsTypeDataDto(repository.save(objectsTypeData));
        }
        documentation.addAll(objectsTypeData.getDocumentations());
        objectsTypeData.setDocumentations(documentation);
        return mapper.mapToObjectsTypeDataDto(repository.save(objectsTypeData));
    }

    @Override
    public ObjectsTypeDataDto updateDocumentation(Long id, List<Long> documentationIds) {
        ObjectsTypeData objectsTypeData = getById(id);
        Map<Long, Documentation>  documentation = objectsTypeData.getDocumentations()
                                                               .stream()
                                                               .collect(Collectors.toMap(Documentation::getId, d -> d));
        for (Long  documentationId : documentationIds) {
            documentation.remove(documentationId);
        }
        objectsTypeData.setDocumentations(new HashSet<>(documentation.values()));
        return mapper.mapToObjectsTypeDataDto(repository.save(objectsTypeData));
    }

    @Override
    public ObjectsTypeDataDto addObjectPassportDataTemplate(Long objectsTypeId, List<Long> templatesIds) {
        ObjectsType objectsType = objectsTypeService.get(objectsTypeId);
        ObjectsTypeData objectsTypeData = repository.findByObjectsType(objectsType);
        Set<ObjectPassportDataTemplate> templates = templateService.getAllByIds(templatesIds);
        if (objectsTypeData == null) {
            objectsTypeData = new ObjectsTypeData();
            objectsTypeData.setObjectsType(objectsType);
            objectsTypeData.setPassportDataTemplates(templates);
            return mapper.mapToObjectsTypeDataDto(repository.save(objectsTypeData));
        }
        templates.addAll(objectsTypeData.getPassportDataTemplates());
        objectsTypeData.setPassportDataTemplates(templates);
        return mapper.mapToObjectsTypeDataDto(repository.save(objectsTypeData));
    }

    @Override
    public ObjectsTypeDataDto updateObjectPassportDataTemplate(Long id, List<Long> templatesIds) {
        ObjectsTypeData objectsTypeData = getById(id);
        Map<Long, ObjectPassportDataTemplate>  templates = objectsTypeData.getPassportDataTemplates()
                                                  .stream()
                                                  .collect(Collectors.toMap(ObjectPassportDataTemplate::getId, o -> o));
        for (Long templatesId : templatesIds) {
            templates.remove(templatesId);
        }
        objectsTypeData.setPassportDataTemplates(new HashSet<>(templates.values()));
        return mapper.mapToObjectsTypeDataDto(repository.save(objectsTypeData));
    }

    private ObjectsTypeData getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("ObjectsTypeData with id=%s not found", id)));
    }
}