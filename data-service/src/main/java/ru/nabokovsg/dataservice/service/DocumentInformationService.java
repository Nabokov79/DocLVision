package ru.nabokovsg.dataservice.service;

import ru.nabokovsg.dataservice.dto.reportData.DocumentInformationDto;
import ru.nabokovsg.dataservice.dto.reportData.DocumentSearchParameters;
import ru.nabokovsg.dataservice.model.Application;
import java.util.List;

public interface DocumentInformationService {

    List<DocumentInformationDto> getAll(DocumentSearchParameters parameters);

    void create(List<Application> applications);
}