package ru.nabokovsg.dataservice.mapper;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.application.ApplicationDto;
import ru.nabokovsg.dataservice.dto.application.NewApplicationDto;
import ru.nabokovsg.dataservice.dto.application.UpdateApplicationDto;
import ru.nabokovsg.dataservice.model.Application;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {

    Application mapToNewApplication(NewApplicationDto applicationDto);

    ApplicationDto mapToApplicationDto(Application application);

    Application mapToUpdateApplication(UpdateApplicationDto applicationDto);

    List<ApplicationDto> mapToApplicationsDto(List<Application> applications);
}