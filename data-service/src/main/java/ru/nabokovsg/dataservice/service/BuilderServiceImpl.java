package ru.nabokovsg.dataservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.Builder;
import ru.nabokovsg.dataservice.dto.ObjectsIds;
import ru.nabokovsg.dataservice.exceptions.BadRequestException;
import ru.nabokovsg.dataservice.model.*;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class BuilderServiceImpl implements BuilderService  {

    private final OrganizationService organizationService;
    private final EmployeeService employeeService;
    private final ControlTypeService controlTypeService;
    private final ObjectsTypeService objectsTypeService;
    private final BuildingService buildingService;

    private Map<Long, Organization> getAllOrganizations(List<Long> ids) {
        return organizationService.getAllByIds(ids).stream().collect(Collectors.toMap(Organization::getId, o -> o));
    }

    private Map<Long, Employee> getAllEmployees(List<Long> ids) {
        return employeeService.getAllByIds(ids).stream().collect(Collectors.toMap(Employee::getId, e -> e));
    }

    private Map<Long, ControlType> getAllControlTypes(List<Long> ids) {
        return controlTypeService.getAllByIds(ids).stream().collect(Collectors.toMap(ControlType::getId, c -> c));
    }

    private Map<Long, Building> getAllBuildings(List<Long> ids) {
        return buildingService.getAllByIds(ids).stream().collect(Collectors.toMap(Building::getId, b -> b));
    }

    private Map<Long, ObjectsType> getAllObjectsTypes(List<Long> ids) {
        return objectsTypeService.getAllByIds(ids).stream().collect(Collectors.toMap(ObjectsType::getId, o -> o));
    }

    @Override
    public Builder getBuilder(List<ObjectsIds> ids, BuilderType type) {
        switch (type) {
            case MEASURING_TOOL, CERTIFICATE -> {
                return new Builder.DataBuilder().organizations(getAllOrganizations(
                                                       Stream.of(ids.stream().map(ObjectsIds::getOrganizationId)
                                                                       , ids.stream().map(ObjectsIds::getManufacturerId)
                                                                       , ids.stream().map(ObjectsIds::getToolOwnerId))
                                                               .flatMap(Function.identity()).distinct().toList()))
                                                .employees(getAllEmployees(ids.stream()
                                                                              .map(ObjectsIds::getEmployeeId)
                                                                              .distinct()
                                                                              .toList()))
                                                .controlTypes(getAllControlTypes(ids.stream()
                                                                                    .map(ObjectsIds::getControlTypeId)
                                                                                    .distinct()
                                                                                    .toList()))
                                                .build();
                                    }
            case OBJECT -> {
                return new Builder.DataBuilder()
                                  .buildings(getAllBuildings(ids.stream()
                                                                .map(ObjectsIds::getBuildingId)
                                                                .distinct()
                                                                .toList()))
                                  .objectsTypes(getAllObjectsTypes(ids.stream()
                                                                      .map(ObjectsIds::getObjectsTypeId)
                                                                      .distinct()
                                                                      .toList()))
                                  .build();
            }
            case LICENSE -> {
                return new Builder.DataBuilder()
                                  .organizations(getAllOrganizations(Stream.of(ids.stream()
                                                                                  .map(ObjectsIds::getOrganizationId)
                                                                             , ids.stream()
                                                                                  .map(ObjectsIds::getIssuedLicenseId))
                                          .flatMap(Function.identity()).distinct().toList()))
                                  .build();
            }
            case SURVEY -> {
                return new Builder.DataBuilder().objectsTypes(getAllObjectsTypes(ids.stream()
                                                                                     .map(ObjectsIds::getObjectsTypeId)
                                                                                     .distinct()
                                                                                     .toList()))
                                                .organizations(getAllOrganizations(ids.stream()
                                                                                     .map(ObjectsIds::getOrganizationId)
                                                                                     .distinct()
                                                                                     .toList()))
                                                .build();
            }
            default ->  throw new BadRequestException(String.format("type= %s is not supported", type));
        }
    }
}