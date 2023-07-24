package ru.nabokovsg.dataservice.service;

import ru.nabokovsg.dataservice.dto.employee.EmployeeDto;
import ru.nabokovsg.dataservice.dto.employee.ShortEmployeeDto;
import ru.nabokovsg.dataservice.dto.employee.UpdateEmployeeDto;
import ru.nabokovsg.dataservice.dto.employee.NewEmployeeDto;

import java.util.List;

public interface EmployeeService {

    ShortEmployeeDto save(NewEmployeeDto employeeDto);

    ShortEmployeeDto update(UpdateEmployeeDto employeeDto);

    EmployeeDto get(Long id);

    List<ShortEmployeeDto> getAll();

    void delete(Long id);
}
