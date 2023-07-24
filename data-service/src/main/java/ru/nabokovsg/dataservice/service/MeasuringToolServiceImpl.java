package ru.nabokovsg.dataservice.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.measuringTool.*;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mapper.EmployeeMapper;
import ru.nabokovsg.dataservice.mapper.MeasuringToolMapper;
import ru.nabokovsg.dataservice.mapper.OrganizationMapper;
import ru.nabokovsg.dataservice.model.MeasuringTool;
import ru.nabokovsg.dataservice.model.QMeasuringTool;
import ru.nabokovsg.dataservice.repository.MeasuringToolRepository;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MeasuringToolServiceImpl implements MeasuringToolService {

    private final MeasuringToolRepository repository;
    private final MeasuringToolMapper mapper;
    private final EntityManager entityManager;
    private final OrganizationService organizationService;
    private final OrganizationMapper organizationMapper;
    private final EmployeeMapper employeeMapper;
    private final EmployeeService employeeService;
    private final ControlTypeService controlTypeService;

    @Override
    public MeasuringToolDto save(NewMeasuringToolDto measuringToolDto) {
        if (measuringToolDto == null) {
            throw new NotFoundException("new measuring tool not found for save");
        }
        MeasuringTool measuringTool = setMeasuringToolValue(mapper.mapToNewMeasuringTool(measuringToolDto),
                                                            mapper.mapToNeMeasuringToolValue(measuringToolDto));
        return mapper.mapToMeasuringToolDto(repository.save(measuringTool));
    }

    @Override
    public MeasuringToolDto update(UpdateMeasuringToolDto measuringToolDto) {
        if (!repository.existsById(measuringToolDto.getId())) {
            throw new NotFoundException(
                    String.format("measuring tool with id = %s not found for update", measuringToolDto.getId())
            );
        }
        MeasuringTool measuringTool = setMeasuringToolValue(mapper.mapToUpdateMeasuringTool(measuringToolDto),
                                                            mapper.mapToUpdateMeasuringToolValue(measuringToolDto));
        return mapper.mapToMeasuringToolDto(repository.save(measuringTool));
    }

    @Override
    public List<MeasuringToolDto> getAll(RequestParameters parameters) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if(parameters.getName() != null) {
            booleanBuilder.and(QMeasuringTool.measuringTool.name.eq(parameters.getName()));
        }
        if(parameters.getModel() != null) {
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
        throw new NotFoundException(String.format("measuring tool with id = %s not found for delete",id));
    }

    public MeasuringTool setMeasuringToolValue(MeasuringTool measuringTool, MeasuringToolIds ids) {
        measuringTool.setType(controlTypeService.get(ids.getTypeId()));
        measuringTool.setOrganization(
                organizationMapper.mapToOrganization(organizationService.get(ids.getOrganizationId()))
        );
        measuringTool.setEmployee(
                employeeMapper.mapToEmployee(employeeService.get(ids.getEmployeeId()))
        );
        measuringTool.setManufacturer(
                organizationMapper.mapToOrganization(organizationService.get(ids.getManufacturerId()))
        );
        measuringTool.setToolOwner(
                organizationMapper.mapToOrganization(organizationService.get(ids.getToolOwnerId()))
        );
        return measuringTool;
    }
}