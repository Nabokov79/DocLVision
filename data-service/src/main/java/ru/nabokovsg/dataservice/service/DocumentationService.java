package ru.nabokovsg.dataservice.service;

import ru.nabokovsg.dataservice.dto.documentation.DocumentationDto;
import ru.nabokovsg.dataservice.dto.documentation.NewDocumentationDto;
import ru.nabokovsg.dataservice.dto.documentation.UpdateDocumentationDto;
import ru.nabokovsg.dataservice.model.Documentation;

import java.util.List;
import java.util.Set;

public interface DocumentationService {

    List<DocumentationDto> save(List<NewDocumentationDto> documentationsDto);

    List<DocumentationDto> update(List<UpdateDocumentationDto> documentationsDto);

    Set<Documentation> getAllByIds(List<Long> documentationsIds);
}