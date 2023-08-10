package ru.nabokovsg.dataservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.workPerformed.NewWorkPerformedDto;
import ru.nabokovsg.dataservice.dto.workPerformed.UpdateWorkPerformedDto;
import ru.nabokovsg.dataservice.dto.workPerformed.WorkPerformedDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mapper.ObjectsIdsMapper;
import ru.nabokovsg.dataservice.mapper.WorkPerformedMapper;
import ru.nabokovsg.dataservice.model.Builder;
import ru.nabokovsg.dataservice.model.BuilderType;
import ru.nabokovsg.dataservice.model.WorkPerformed;
import ru.nabokovsg.dataservice.repository.WorkPerformedRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkPerformedServiceImpl implements WorkPerformedService {

    private final WorkPerformedRepository repository;
    private final WorkPerformedMapper mapper;
    private final BuilderFactoryService factoryService;
    private final ObjectsIdsMapper objectsIdsMapper;

    @Override
    public List<WorkPerformedDto> save(List<NewWorkPerformedDto> worksPerformedDto) {
        Builder builder = factoryService.getBuilder(worksPerformedDto.stream()
                                                                    .map(objectsIdsMapper::mapFromNewWorkPerformedDto)
                                                                    .toList()
                                                  , BuilderType.WORK_PERFORMED);
        List<WorkPerformed> worksPerformed = new ArrayList<>();
        for (NewWorkPerformedDto workPerformedDto : worksPerformedDto) {
            WorkPerformed workPerformed = mapper.mapFromNewWorkPerformed(workPerformedDto);
            workPerformed.setReportingDocument(builder.getReportingDocuments()
                                                      .get(workPerformedDto
                                                      .getReportingDocumentId()));
            worksPerformed.add(workPerformed);
        }
        return mapper.mapToWorkPerformedDto(repository.saveAll(worksPerformed));
    }

    @Override
    public List<WorkPerformedDto> update(List<UpdateWorkPerformedDto> worksPerformedDto) {
        validateIds(worksPerformedDto.stream().map(UpdateWorkPerformedDto::getId).toList());
        Builder builder = factoryService.getBuilder(worksPerformedDto.stream()
                                                                   .map(objectsIdsMapper::mapFromUpdateWorkPerformedDto)
                                                                   .toList()
                                                  , BuilderType.WORK_PERFORMED);
        List<WorkPerformed> worksPerformed = new ArrayList<>();
        for (UpdateWorkPerformedDto workPerformedDto : worksPerformedDto) {
            WorkPerformed workPerformed = mapper.mapFromUpdateWorkPerformed(workPerformedDto);
            workPerformed.setReportingDocument(builder.getReportingDocuments()
                                                      .get(workPerformedDto
                                                      .getReportingDocumentId()));
            worksPerformed.add(workPerformed);
        }
        return mapper.mapToWorkPerformedDto(repository.saveAll(worksPerformed));
    }

    @Override
    public List<WorkPerformedDto> getAll() {
        return mapper.mapToWorkPerformedDto(repository.findAll());
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("work performed with id=%s not found for delete", id));
    }

    private void validateIds(List<Long> ids) {
        Map<Long, WorkPerformed> works = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(WorkPerformed::getId, m -> m));
        if (works.size() != ids.size() || works.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(works.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("work performed with ids= %s not found", ids));
        }
    }
}