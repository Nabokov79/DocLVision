package ru.nabokovsg.dataservice.service;

import ru.nabokovsg.dataservice.dto.object.NewObjectDto;
import ru.nabokovsg.dataservice.dto.object.UpdateObjectDto;
import ru.nabokovsg.dataservice.dto.object.ObjectDto;
import ru.nabokovsg.dataservice.model.Objects;

import java.util.List;

public interface ObjectsService {

    List<ObjectDto> save(List<NewObjectDto> objectDto );

    List<ObjectDto> update(List<UpdateObjectDto> objectDto);

    List<Objects> getAllById(List<Long> ids);

     void delete(Long id);
}