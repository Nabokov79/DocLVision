package ru.nabokovsg.dataservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.building.BuildingDto;
import ru.nabokovsg.dataservice.dto.building.NewBuildingDto;
import ru.nabokovsg.dataservice.dto.building.UpdateBuildingDto;
import ru.nabokovsg.dataservice.exceptions.BadRequestException;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mapper.BuildingMapper;
import ru.nabokovsg.dataservice.mapper.DepartmentMapper;
import ru.nabokovsg.dataservice.model.Building;
import ru.nabokovsg.dataservice.repository.BuildingRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BuildingServiceImpl implements BuildingService {

    private final BuildingRepository repository;
    private final BuildingMapper mapper;
    private final DepartmentService departmentService;
    private final DepartmentMapper departmentMapper;
    private final RequisitesService requisitesService;

    @Override
    public BuildingDto save(NewBuildingDto buildingDto) {
        Building building = mapper.mapToNewBuilding(buildingDto);
        building.setRequisites(requisitesService.save(buildingDto.getRequisites()));
        building.setDepartment(
                                departmentMapper.mapToDepartment(departmentService.get(buildingDto.getDepartmentId())));
        return mapper.mapToBuildingDto(repository.save(building));
    }

    @Override
    public BuildingDto update(UpdateBuildingDto buildingDto) {
        if (repository.existsById(buildingDto.getId())) {
            Building building = mapper.mapToUpdateBuilding(buildingDto);
            building.setRequisites(requisitesService.update(buildingDto.getRequisites()));
            building.setDepartment(
                                departmentMapper.mapToDepartment(departmentService.get(buildingDto.getDepartmentId())));
            return mapper.mapToBuildingDto(repository.save(building));
        }
        throw new BadRequestException(String.format("building with id=%s not found for update",buildingDto.getId()));
    }

    @Override
    public List<BuildingDto> getAll(Long departmentId) {
        return mapper.mapToBuildingsDto(
                repository.findAllByDepartment(departmentMapper.mapToDepartment(departmentService.get(departmentId)))
        );
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Building with id=%s not found for delete.", id));
    }
}