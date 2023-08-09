package ru.nabokovsg.dataservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.objectsType.NewObjectsTypeDto;
import ru.nabokovsg.dataservice.dto.objectsType.ObjectsTypeDto;
import ru.nabokovsg.dataservice.dto.objectsType.UpdateObjectsTypeDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mapper.ObjectsTypeMapper;
import ru.nabokovsg.dataservice.model.ObjectsType;
import ru.nabokovsg.dataservice.repository.ObjectsTypeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ObjectsTypeServiceImpl implements ObjectsTypeService {

    private final ObjectsTypeRepository repository;
    private final ObjectsTypeMapper mapper;

    @Override
    public List<ObjectsTypeDto> save(List<NewObjectsTypeDto> objectsTypeDto) {
        return mapper.mapToObjectsTypeDto(repository.saveAll(mapper.mapToNewObjectsType(objectsTypeDto)));
    }

    @Override
    public List<ObjectsTypeDto> update(List<UpdateObjectsTypeDto> objectsTypeDto) {
        validateIds(objectsTypeDto.stream().map(UpdateObjectsTypeDto::getId).toList());
        return mapper.mapToObjectsTypeDto(repository.saveAll(mapper.mapToUpdateObjectsType(objectsTypeDto)));
    }

    @Override
    public ObjectsType get(Long id) {
        return repository.findById(id)
                      .orElseThrow(() -> new NotFoundException(String.format("Objects type with id=%s not found", id)));
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("measuring tool with id = %s not found for delete", id));
    }

    private void validateIds(List<Long> ids) {
        Map<Long, ObjectsType> objectsTypes = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(ObjectsType::getId, o -> o));
        if (objectsTypes.size() != ids.size() || objectsTypes.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(objectsTypes.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("objects types with ids= %s not found", ids));
        }
    }
}