package ru.nabokovsg.dataservice.service;

import ru.nabokovsg.dataservice.dto.application.ApplicationDto;
import ru.nabokovsg.dataservice.dto.application.ApplicationSearchParameters;
import ru.nabokovsg.dataservice.dto.application.NewApplicationDto;
import ru.nabokovsg.dataservice.dto.application.UpdateApplicationDto;

import java.util.List;

public interface ApplicationService {

    List<ApplicationDto> save(List<NewApplicationDto> applicationsDto);

    List<ApplicationDto> update(List<UpdateApplicationDto> applicationsDto);

    ApplicationDto get(Long id);

   List<ApplicationDto> getAll(ApplicationSearchParameters parameters);
}