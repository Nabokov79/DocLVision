package ru.nabokovsg.dataservice.service;

import ru.nabokovsg.dataservice.dto.department.DepartmentDto;
import ru.nabokovsg.dataservice.dto.department.NewDepartmentDto;
import ru.nabokovsg.dataservice.dto.department.ShortDepartmentDto;
import ru.nabokovsg.dataservice.dto.department.UpdateDepartmentDto;
import java.util.List;

public interface DepartmentService {

    DepartmentDto save(NewDepartmentDto departmentDto);

    DepartmentDto update(UpdateDepartmentDto departmentDto);

    DepartmentDto get(Long id);

    List<ShortDepartmentDto> getAll(Long branchId);

    void delete(Long id);
}