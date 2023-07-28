package ru.nabokovsg.dataservice.mapper;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.employee.EmployeeDto;
import ru.nabokovsg.dataservice.dto.employee.ShortEmployeeDto;
import ru.nabokovsg.dataservice.dto.employee.UpdateEmployeeDto;
import ru.nabokovsg.dataservice.dto.employee.NewEmployeeDto;
import ru.nabokovsg.dataservice.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    Employee mapToEmployee(NewEmployeeDto employeeDto);

    EmployeeDto mapToEmployeeDto(Employee employee);

    Employee mapToUpdateEmployee(UpdateEmployeeDto employeeDto);

    ShortEmployeeDto mapToEmployeeShortDto(Employee employee);
}