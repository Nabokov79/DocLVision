package ru.nabokovsg.dataservice.service;

import ru.nabokovsg.dataservice.dto.norms.NewNormsDto;
import ru.nabokovsg.dataservice.dto.norms.UpdateNormsDto;
import ru.nabokovsg.dataservice.model.Norm;

import java.util.List;
import java.util.Set;

public interface NormsService {

    Set<Norm> save(List<NewNormsDto> normsDto);

    Set<Norm> update(List<UpdateNormsDto> normsDto);
}