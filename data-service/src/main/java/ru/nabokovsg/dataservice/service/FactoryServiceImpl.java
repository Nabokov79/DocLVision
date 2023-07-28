package ru.nabokovsg.dataservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.Factory;
import ru.nabokovsg.dataservice.dto.ObjectsIds;
import ru.nabokovsg.dataservice.model.ControlType;
import ru.nabokovsg.dataservice.model.Employee;
import ru.nabokovsg.dataservice.model.Organization;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FactoryServiceImpl implements FactoryService {

    private final OrganizationService organizationService;
    private final EmployeeService employeeService;
    private final ControlTypeService controlTypeService;

    @Override
    public Factory create(List<ObjectsIds> objects) {
        List<Long> organizationId = new ArrayList<>();
        organizationId.addAll(objects.stream()
                .map(ObjectsIds::getOrganizationId)
                .toList());
        organizationId.addAll(objects.stream()
                .map(ObjectsIds::getManufacturerId)
                .toList());
        organizationId.addAll(objects.stream()
                .map(ObjectsIds::getToolOwnerId)
                .toList());
        return new Factory(organizationService.getAllByIds(organizationId.stream()
                        .distinct()
                        .toList())
                .stream()
                .collect(Collectors.toMap(Organization::getId, o -> o))
                , employeeService.getAllByIds(objects.stream()
                        .map(ObjectsIds::getEmployeeId)
                        .distinct()
                        .toList())
                .stream()
                .collect(Collectors.toMap(Employee::getId, e -> e))
                , controlTypeService.getAllByIds(objects.stream()
                        .map(ObjectsIds::getControlTypeId)
                        .distinct()
                        .toList())
                .stream()
                .collect(Collectors.toMap(ControlType::getId, c -> c))
        );
    }
}
