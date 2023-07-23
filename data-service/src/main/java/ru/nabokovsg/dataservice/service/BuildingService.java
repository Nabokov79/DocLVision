package ru.nabokovsg.dataservice.service;

import ru.nabokovsg.dataservice.dto.building.BuildingDto;
import ru.nabokovsg.dataservice.dto.building.NewBuildingDto;
import ru.nabokovsg.dataservice.dto.building.UpdateBuildingDto;
import java.util.List;

public interface BuildingService {

    BuildingDto save(NewBuildingDto buildingDto);

    BuildingDto update(UpdateBuildingDto buildingDto);

    List<BuildingDto> getAll(Long departmentId);

    void  delete(Long id);
}