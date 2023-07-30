package ru.nabokovsg.dataservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.Builder;
import ru.nabokovsg.dataservice.dto.object.NewObjectDto;
import ru.nabokovsg.dataservice.dto.object.UpdateObjectDto;
import ru.nabokovsg.dataservice.dto.object.ObjectDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mapper.IdsMapper;
import ru.nabokovsg.dataservice.mapper.ObjectsMapper;
import ru.nabokovsg.dataservice.model.BuilderType;
import ru.nabokovsg.dataservice.model.Objects;
import ru.nabokovsg.dataservice.repository.ObjectsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ObjectsServiceImpl implements ObjectsService {

    private final ObjectsRepository repository;
    private final ObjectsMapper mapper;
    private final IdsMapper idsMapper;
    private final BuilderService service;

    @Override
    public List<ObjectDto> save(List<NewObjectDto> objectsDto) {
        Builder builder = service.getBuilder(objectsDto.stream().map(idsMapper::mapFromNewObjectDto).toList(), BuilderType.OBJECT);
        List<Objects> objects = new ArrayList<>();
        for (NewObjectDto objectDto : objectsDto) {
            Objects object = mapper.mapToNewObjects(objectDto);
            object.setObjectsType(builder.getObjectsTypes().get(objectDto.getObjectsTypeId()));
            object.setBuilding(builder.getBuildings().get(objectDto.getBuildingId()));
            objects.add(object);
        }
        return mapper.mapToObjectDto(repository.saveAll(objects));
    }

    @Override
    public List<ObjectDto> update(List<UpdateObjectDto> objectsDto) {
        validateIds(objectsDto.stream().map(UpdateObjectDto::getId).toList());
        Builder builder = service.getBuilder(objectsDto.stream().map(idsMapper::mapFromUpdateObjectDto).toList(), BuilderType.OBJECT);
        List<Objects> objects = new ArrayList<>();
        for (UpdateObjectDto objectDto : objectsDto) {
            Objects object = mapper.mapToUpdateObjects(objectDto);
            object.setObjectsType(builder.getObjectsTypes().get(objectDto.getObjectsTypeId()));
            object.setBuilding(builder.getBuildings().get(objectDto.getBuildingId()));
            objects.add(object);
        }
        return mapper.mapToObjectDto(repository.saveAll(objects));
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("objects with id= %s not found for delete", id));
    }

    private void validateIds(List<Long> ids) {
        Map<Long, Objects> objects = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(Objects::getId, m -> m));
        if (objects.size() != ids.size() || objects.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(objects.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("objects with ids= %s not found", ids));
        }
    }
}