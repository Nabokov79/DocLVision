package ru.nabokovsg.dataservice.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.model.Builder;
import ru.nabokovsg.dataservice.model.ObjectsIds;
import ru.nabokovsg.dataservice.dto.measuringTool.*;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mapper.ObjectsIdsMapper;
import ru.nabokovsg.dataservice.mapper.MeasuringToolMapper;
import ru.nabokovsg.dataservice.model.*;
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
    private final ObjectsIdsMapper objectsIdsMapper;
    private final BuilderFactoryService factoryService;

    @Override
    public List<MeasuringToolDto> save(List<NewMeasuringToolDto> measuringToolsDto) {
        Builder builder = factoryService.getBuilder(measuringToolsDto.stream()
                                                                     .map(objectsIdsMapper::mapFromNewMeasuringToolDto)
                                                                     .toList()
                                                  , BuilderType.MEASURING_TOOL);
        List<MeasuringTool> measuringTools = new ArrayList<>();
        for (NewMeasuringToolDto measuringToolDto : measuringToolsDto) {
            measuringTools.add(set(mapper.mapToNewMeasuringTool(measuringToolDto)
                                 , objectsIdsMapper.mapFromNewMeasuringToolDto(measuringToolDto)
                                 , builder));
        }
        return mapper.mapToMeasuringToolDto(repository.saveAll(measuringTools));
    }

    @Override
    public List<MeasuringToolDto> update(List<UpdateMeasuringToolDto> measuringToolsDto) {
        validateIds(measuringToolsDto.stream().map(UpdateMeasuringToolDto::getId).toList());
        Builder builder = factoryService.getBuilder(measuringToolsDto.stream()
                                                                    .map(objectsIdsMapper::mapFromUpdateMeasuringToolDto)
                                                                    .toList()
                                                  , BuilderType.MEASURING_TOOL);
        List<MeasuringTool> measuringTools = new ArrayList<>();
        for (UpdateMeasuringToolDto measuringToolDto : measuringToolsDto) {
            measuringTools.add(set(mapper.mapToUpdateMeasuringTool(measuringToolDto)
                                 , objectsIdsMapper.mapFromUpdateMeasuringToolDto(measuringToolDto)
                                 , builder));
        }
        return mapper.mapToMeasuringToolDto(repository.saveAll(measuringTools));
    }

    @Override
    public List<MeasuringToolDto> getAll(RequestParameters parameters) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (parameters.getName() != null) {
            booleanBuilder.and(QMeasuringTool.measuringTool.toll.eq(parameters.getName()));
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
            booleanBuilder.and(QMeasuringTool.measuringTool.controlType.id.eq(parameters.getTypeId()));
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
                .stream().collect(Collectors.toMap(MeasuringTool::getId, m -> m));
        if (measuringTools.size() != ids.size() || measuringTools.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(measuringTools.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("measuring tools with ids= %s not found", ids));
        }
    }

    private MeasuringTool set(MeasuringTool measuringTool, ObjectsIds ids, Builder builder) {
        measuringTool.setOrganization(builder.getOrganizations().get(ids.getOrganizationId()));
        measuringTool.setToolOwner(builder.getOrganizations().get(ids.getToolOwnerId()));
        measuringTool.setManufacturer(builder.getOrganizations().get(ids.getManufacturerId()));
        if (ids.getEmployeeId() != null) {
            measuringTool.setEmployee(builder.getEmployees().get(ids.getEmployeeId()));
        }
        measuringTool.setControlType(builder.getControlTypes().get(ids.getControlTypeId()));
        return measuringTool;
    }
}