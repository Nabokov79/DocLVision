package ru.nabokovsg.dataservice.mapper;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.documentation.DocumentationDto;
import ru.nabokovsg.dataservice.dto.documentation.NewDocumentationDto;
import ru.nabokovsg.dataservice.dto.documentation.UpdateDocumentationDto;
import ru.nabokovsg.dataservice.model.Documentation;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DocumentationMapper {

    List<Documentation> mapToNewDocumentation(List<NewDocumentationDto> documentationsDto);

    List<DocumentationDto> mapToDocumentationDto(List<Documentation> documentations);

    List<Documentation> mapToUpdateDocumentation(List<UpdateDocumentationDto> documentationsDto);
}