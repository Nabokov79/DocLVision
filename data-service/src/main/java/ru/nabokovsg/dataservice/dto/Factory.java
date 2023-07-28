package ru.nabokovsg.dataservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.model.ControlType;
import ru.nabokovsg.dataservice.model.Employee;
import ru.nabokovsg.dataservice.model.Organization;

import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
public class Factory {

    private Map<Long, Organization> organizations;
    private Map<Long, Employee> employees;
    private Map<Long, ControlType> controlTypes;
}
