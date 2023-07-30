package ru.nabokovsg.dataservice.service;

import ru.nabokovsg.dataservice.dto.building.BuildingDto;
import ru.nabokovsg.dataservice.dto.building.NewBuildingDto;
import ru.nabokovsg.dataservice.dto.building.UpdateBuildingDto;
import ru.nabokovsg.dataservice.model.Building;
import java.util.List;

public interface BuildingService {

    BuildingDto save(NewBuildingDto buildingDto);

    BuildingDto update(UpdateBuildingDto buildingDto);

    List<BuildingDto> getAll(Long departmentId);

    List<Building> getAllByIds(List<Long> ids);

    void  delete(Long id);
}