package ru.nabokovsg.dataservice.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.measuringTool.*;
import ru.nabokovsg.dataservice.dto.organization.OrganizationDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mapper.EmployeeMapper;
import ru.nabokovsg.dataservice.mapper.MeasuringToolMapper;
import ru.nabokovsg.dataservice.mapper.OrganizationMapper;
import ru.nabokovsg.dataservice.model.ControlType;
import ru.nabokovsg.dataservice.model.Employee;
import ru.nabokovsg.dataservice.model.MeasuringTool;
import ru.nabokovsg.dataservice.model.Organization;
import ru.nabokovsg.dataservice.model.QMeasuringTool;
import ru.nabokovsg.dataservice.repository.MeasuringToolRepository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeasuringToolServiceImpl implements MeasuringToolService {

    private final MeasuringToolRepository repository;
    private final MeasuringToolMapper mapper;
    private final EntityManager entityManager;
    private final OrganizationService organizationService;
    private final EmployeeService employeeService;
    private final ControlTypeService controlTypeService;

    @Override
    public List<MeasuringToolDto> save(List<NewMeasuringToolDto> measuringToolsDto) {
        Map<Long, Organization> organizations = organizationService.getAllByIds(measuringToolsDto
                                                                .stream()
                                                                .map(NewMeasuringToolDto::getOrganizationId)
                                                                .toList())
                                                                .stream()
                                                                .collect(Collectors.toMap(Organization::getId, o -> o));
        Map<Long, Employee> employees = employeeService.getAllByIds(measuringToolsDto
                                                            .stream()
                                                            .map(NewMeasuringToolDto::getEmployeeId)
                                                            .toList())
                                                            .stream()
                                                            .collect(Collectors.toMap(Employee::getId, e -> e));
        Map<Long, ControlType> controlTypes = controlTypeService.getAllByIds(measuringToolsDto
                                                                .stream()
                                                                .map(NewMeasuringToolDto::getControlTypeId)
                                                                .toList())
                                                                .stream()
                                                                .collect(Collectors.toMap(ControlType::getId, c -> c));
        List<MeasuringTool> measuringTools = new ArrayList<>();
        for (NewMeasuringToolDto measuringToolDto : measuringToolsDto) {
            MeasuringTool measuringTool = mapper.mapToNewMeasuringTool(measuringToolDto);
            measuringTool.setOrganization(organizations.get(measuringToolDto.getOrganizationId()));
            measuringTool.setToolOwner(organizations.get(measuringToolDto.getToolOwnerId()));
            measuringTool.setManufacturer(organizations.get(measuringToolDto.getManufacturerId()));
            measuringTool.setEmployee(employees.get(measuringToolDto.getEmployeeId()));
            measuringTool.setControlType(controlTypes.get(measuringToolDto.getControlTypeId()));
            measuringTools.add(measuringTool);
        }
        return mapper.mapToMeasuringToolDto(repository.saveAll(measuringTools));
    }

    @Override
    public List<MeasuringToolDto> update(List<UpdateMeasuringToolDto> measuringToolsDto) {
        validateIds(measuringToolsDto.stream().map(UpdateMeasuringToolDto::getId).toList());
        Map<Long, Organization> organizations = organizationService.getAllByIds(measuringToolsDto
                                                                .stream()
                                                                .map(UpdateMeasuringToolDto::getOrganizationId)
                                                                .toList())
                                                                .stream()
                                                                .collect(Collectors.toMap(Organization::getId, o -> o));
        Map<Long, Employee> employees = employeeService.getAllByIds(measuringToolsDto
                                                            .stream()
                                                            .map(UpdateMeasuringToolDto::getEmployeeId)
                                                            .toList())
                                                            .stream()
                                                            .collect(Collectors.toMap(Employee::getId, e -> e));
        Map<Long, ControlType> controlTypes = controlTypeService.getAllByIds(measuringToolsDto
                                                                .stream()
                                                                .map(UpdateMeasuringToolDto::getControlTypeId)
                                                                .toList())
                                                                .stream()
                                                                .collect(Collectors.toMap(ControlType::getId, c -> c));
        List<MeasuringTool> measuringTools = new ArrayList<>();
        for (UpdateMeasuringToolDto measuringToolDto : measuringToolsDto) {
            MeasuringTool measuringTool = mapper.mapToUpdateMeasuringTool(measuringToolDto);
            measuringTool.setOrganization(organizations.get(measuringToolDto.getOrganizationId()));
            measuringTool.setToolOwner(organizations.get(measuringToolDto.getToolOwnerId()));
            measuringTool.setManufacturer(organizations.get(measuringToolDto.getManufacturerId()));
            measuringTool.setEmployee(employees.get(measuringToolDto.getEmployeeId()));
            measuringTool.setControlType(controlTypes.get(measuringToolDto.getControlTypeId()));
            measuringTools.add(measuringTool);
        }
        return mapper.mapToMeasuringToolDto(repository.saveAll(measuringTools));
    }

    @Override
    public List<MeasuringToolDto> getAll(RequestParameters parameters) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (parameters.getName() != null) {
            booleanBuilder.and(QMeasuringTool.measuringTool.name.eq(parameters.getName()));
        }
        if (parameters.getModel() != null) {
            booleanBuilder.and(QMeasuringTool.measuringTool.model.eq(parameters.getModel()));
        }
        if (parameters.getWorkNumber() != null) {
            booleanBuilder.and(QMeasuringTool.measuringTool.workNumber.eq(parameters.getWorkNumber()));
        }
        if (parameters.getManufacturing() != null) {
            booleanBuilder.and(QMeasuringTool.measuringTool.manufacturing.after(parameters.getManufacturing()));
        }
        if (parameters.getExploitation() != null) {
            booleanBuilder.and(QMeasuringTool.measuringTool.exploitation.eq(parameters.getManufacturing()));
        }
        if (parameters.getManufacturerId() != null) {
            booleanBuilder.and(QMeasuringTool.measuringTool.manufacturer.id.eq(parameters.getManufacturerId()));
        }
        if (parameters.getOrganizationId() != null) {
            booleanBuilder.and(QMeasuringTool.measuringTool.organization.id.eq(parameters.getOrganizationId()));
        }
        if (parameters.getTypeId() != null) {
            booleanBuilder.and(QMeasuringTool.measuringTool.type.id.eq(parameters.getTypeId()));
        }
        if (parameters.getEmployeeId() != null) {
            booleanBuilder.and(QMeasuringTool.measuringTool.employee.id.eq(parameters.getEmployeeId()));
        }
        QMeasuringTool measuringTool = QMeasuringTool.measuringTool;
        JPAQueryFactory qf = new JPAQueryFactory(entityManager);
        JPAQuery<MeasuringTool> query = qf.from(measuringTool)
                .select(measuringTool)
                .where(booleanBuilder);
        return mapper.mapToMeasuringToolsDto(query.fetch());
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("measuring tool with id = %s not found for delete", id));
    }

    private void validateIds(List<Long> ids) {
        Map<Long, MeasuringTool> measuringTools = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(MeasuringTool::getId, subheading -> subheading));
        if (measuringTools.size() != ids.size() || measuringTools.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(measuringTools.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("measuring tools with ids= %s not found", ids));
        }
    }
}