package ru.nabokovsg.dataservice.service;

import ru.nabokovsg.dataservice.dto.workPerformed.NewWorkPerformedDto;
import ru.nabokovsg.dataservice.dto.workPerformed.UpdateWorkPerformedDto;
import ru.nabokovsg.dataservice.dto.workPerformed.WorkPerformedDto;

import java.util.List;

public interface WorkPerformedService {

    List<WorkPerformedDto> save(List<NewWorkPerformedDto> worksPerformedDto);

    List<WorkPerformedDto> update(List<UpdateWorkPerformedDto> worksPerformedDto);

    List<WorkPerformedDto> getAll();

    void delete(Long id);
}