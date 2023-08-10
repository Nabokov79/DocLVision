package ru.nabokovsg.dataservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.model.Builder;
import ru.nabokovsg.dataservice.model.ObjectsIds;
import ru.nabokovsg.dataservice.exceptions.BadRequestException;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.model.*;
import ru.nabokovsg.dataservice.repository.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class BuilderFactoryServiceImpl implements BuilderFactoryService {

    private final OrganizationRepository organizationRepository;
    private final EmployeeRepository employeeRepository;
    private final ControlTypeRepository controlTypeRepository;
    private final BuildingRepository buildingRepository;
    private final ObjectsTypeRepository objectsTypeRepository;
    private final ObjectsRepository objectsRepository;
    private final ObjectPassportDataTemplateRepository templateRepository;
    private final ReportingDocumentRepository reportingDocumentRepository;
    private final AddressRepository addressRepository;
    private final WorkPerformedRepository workPerformedRepository;

    @Override
    public Builder getBuilder(List<ObjectsIds> ids, BuilderType type) {
        switch (type) {
            case MEASURING_TOOL -> {
                return new Builder.DataBuilder()
                                  .organizations(getOrganizations(
                                                               Stream.of(ids.stream().map(ObjectsIds::getOrganizationId)
                                                                       , ids.stream().map(ObjectsIds::getManufacturerId)
                                                                       , ids.stream().map(ObjectsIds::getToolOwnerId))
                                                               .flatMap(Function.identity()).distinct().toList()))
                                  .employees(getEmployees(ids.stream()
                                                             .map(ObjectsIds::getEmployeeId)
                                                             .toList()))
                                  .controlTypes(getControlTypes(ids.stream()
                                                                   .map(ObjectsIds::getControlTypeId)
                                                                   .toList()))
                                  .build();
            }
            case CERTIFICATE -> {
                return new Builder.DataBuilder()
                                  .organizations(getOrganizations(ids.stream()
                                                                     .map(ObjectsIds::getOrganizationId)
                                                                     .toList()))
                                  .employees(getEmployees(ids.stream()
                                                             .map(ObjectsIds::getEmployeeId)
                                                             .toList()))
                                  .controlTypes(getControlTypes(ids.stream()
                                                                   .map(ObjectsIds::getControlTypeId)
                                                                   .toList()))
                                  .build();
            }
            case LICENSE -> {
                return new Builder.DataBuilder()
                                  .organizations(getOrganizations(Stream.of(ids.stream()
                                                                               .map(ObjectsIds::getOrganizationId)
                                                                          , ids.stream()
                                                                               .map(ObjectsIds::getIssuedLicenseId))
                                                                 .flatMap(Function.identity()).distinct().toList()))
                                  .build();
            }
            case OBJECT -> {
                return new Builder.DataBuilder()
                                  .buildings(getBuildings(ids.stream()
                                                             .map(ObjectsIds::getBuildingId)
                                                             .toList()))
                                  .objectsTypes(getObjectsTypes(ids.stream()
                                                                   .map(ObjectsIds::getObjectsTypeId)
                                                                   .toList()))
                                  .build();
            }
            case SURVEY, REPAIR -> {
                return new Builder.DataBuilder()
                                  .organizations(getOrganizations(ids.stream()
                                                                     .map(ObjectsIds::getOrganizationId)
                                                                     .toList()))
                                  .objects(getObjects(ids.stream()
                                                         .map(ObjectsIds::getObjectId)
                                                         .toList()))
                                  .build();
            }
            case PASSPORT_DATA -> {
                return new Builder.DataBuilder()
                                  .templates(getTemplates(ids.stream()
                                                             .map(ObjectsIds::getTemplateId)
                                                             .toList()))
                                  .objects(getObjects(ids.stream()
                                                         .map(ObjectsIds::getObjectId)
                                                         .toList()))
                                  .build();
            }
            case WORK_PERFORMED -> {
                return new Builder.DataBuilder()
                                  .reportingDocuments(getReportingDocument(ids.stream()
                                                                              .map(ObjectsIds::getReportingDocumentId)
                                                                              .toList()))
                                  .build();
            }
            case APPLICATIONS -> {
                return new Builder.DataBuilder()
                                  .addresses(getAddresses(ids.stream()
                                                             .map(ObjectsIds::getAddressId)
                                                             .toList()))
                                  .objects(getObjects(ids.stream()
                                                         .map(ObjectsIds::getObjectId)
                                                         .toList()))
                                  .worksPerformed(getWorksPerformed(ids.stream()
                                                                       .map(ObjectsIds::getWorkPerformedId)
                                                                       .toList()))
                                  .employees(getEmployees(ids.stream()
                                          .map(ObjectsIds::getEmployeesIds)
                                          .flatMap(Collection::stream)
                                          .toList()))
                                  .build();
            }
            default ->  throw new BadRequestException(String.format("type= %s is not supported", type));
        }
    }

    private Map<Long, Organization> getOrganizations(List<Long> ids) {
        if (ids.isEmpty()) {
            throw new NotFoundException(String.format("Organization with ids=%s not found", ids));
        }
        return organizationRepository.findAllById(ids).stream().collect(Collectors.toMap(Organization::getId, o -> o));
    }

    private Map<Long, Employee> getEmployees(List<Long> ids) {
        if (ids.isEmpty()) {
            throw new NotFoundException(String.format("Employees with ids=%s not found", ids));
        }
        return employeeRepository.findAllById(ids).stream().collect(Collectors.toMap(Employee::getId, e -> e));
    }

    private Map<Long, ControlType> getControlTypes(List<Long> ids) {
        if (ids.isEmpty()) {
            throw new NotFoundException(String.format("Control type with ids=%s not found", ids));
        }
        return controlTypeRepository.findAllById(ids).stream().collect(Collectors.toMap(ControlType::getId, c -> c));
    }

    private Map<Long, Building> getBuildings(List<Long> ids) {
        if (ids.isEmpty()) {
            throw new NotFoundException(String.format("Buildings with ids=%s not found", ids));
        }
        return buildingRepository.findAllById(ids).stream().collect(Collectors.toMap(Building::getId, b -> b));
    }

    private Map<Long, ObjectsType> getObjectsTypes(List<Long> ids) {
        if (ids.isEmpty()) {
            throw new NotFoundException(String.format("Objects types with ids=%s not found", ids));
        }
        return objectsTypeRepository.findAllById(ids).stream().collect(Collectors.toMap(ObjectsType::getId, o -> o));
    }

    private Map<Long, Objects> getObjects(List<Long> ids) {
        if (ids.isEmpty()) {
            throw new NotFoundException(String.format("Objects with ids=%s not found", ids));
        }
        return objectsRepository.findAllById(ids).stream().collect(Collectors.toMap(Objects::getId, o -> o));
    }

    private Map<Long, ObjectPassportDataTemplate> getTemplates(List<Long> ids) {
        if (ids.isEmpty()) {
            throw new NotFoundException(String.format("Templates with ids=%s not found", ids));
        }
        return templateRepository.findAllById(ids).stream()
                                                  .collect(Collectors.toMap(ObjectPassportDataTemplate::getId, o -> o));
    }

    private Map<Long, ReportingDocument> getReportingDocument(List<Long> ids) {
        if (ids.isEmpty()) {
            throw new NotFoundException(String.format("Reporting document with ids=%s not found", ids));
        }
        return reportingDocumentRepository.findAllById(ids).stream()
                                                           .collect(Collectors.toMap(ReportingDocument::getId, o -> o));
    }

    private Map<Long, Address> getAddresses(List<Long> ids) {
        if (ids.isEmpty()) {
            throw new NotFoundException(String.format("Addresses with ids=%s not found", ids));
        }
        return addressRepository.findAllById(ids).stream()
                                                 .collect(Collectors.toMap(Address::getId, o -> o));
    }

    private Map<Long, WorkPerformed> getWorksPerformed(List<Long> ids) {
        if (ids.isEmpty()) {
            throw new NotFoundException(String.format("WorkPerformed with ids=%s not found", ids));
        }
        return workPerformedRepository.findAllById(ids).stream()
                                                       .collect(Collectors.toMap(WorkPerformed::getId, o -> o));
    }
}