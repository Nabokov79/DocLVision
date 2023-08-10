package ru.nabokovsg.dataservice.mapper;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.workPerformed.NewWorkPerformedDto;
import ru.nabokovsg.dataservice.dto.workPerformed.UpdateWorkPerformedDto;
import ru.nabokovsg.dataservice.dto.workPerformed.WorkPerformedDto;
import ru.nabokovsg.dataservice.model.WorkPerformed;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WorkPerformedMapper {

    WorkPerformed mapFromNewWorkPerformed(NewWorkPerformedDto workPerformedDto);

    WorkPerformed mapFromUpdateWorkPerformed(UpdateWorkPerformedDto workPerformedDto);

    List<WorkPerformedDto> mapToWorkPerformedDto(List<WorkPerformed> worksPerformed);
}