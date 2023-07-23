package ru.nabokovsg.dataservice.mapper;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.building.BuildingDto;
import ru.nabokovsg.dataservice.dto.building.NewBuildingDto;
import ru.nabokovsg.dataservice.dto.building.UpdateBuildingDto;
import ru.nabokovsg.dataservice.model.Building;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface BuildingMapper {

    Building mapToNewBuilding(NewBuildingDto buildingDto);

    Building mapToUpdateBuilding(UpdateBuildingDto buildingDto);

    BuildingDto mapToBuildingDto(Building building);

    List<BuildingDto> mapToBuildingsDto(Set<Building> buildings);
}