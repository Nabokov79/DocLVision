package ru.nabokovsg.dataservice.mapper;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.department.DepartmentDto;
import ru.nabokovsg.dataservice.dto.department.NewDepartmentDto;
import ru.nabokovsg.dataservice.dto.department.ShortDepartmentDto;
import ru.nabokovsg.dataservice.dto.department.UpdateDepartmentDto;
import ru.nabokovsg.dataservice.model.Department;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    Department mapToNewDepartment(NewDepartmentDto departmentDto);

    Department mapToUpdateDepartment(UpdateDepartmentDto departmentDto);

    DepartmentDto mapToDepartmentDto(Department department);

    List<ShortDepartmentDto> mapToShortDepartmentDto(Set<Department> departments);

    Department mapToDepartment(DepartmentDto departmentDto);
}