package ru.nabokovsg.dataservice.mapper;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.reportData.DocumentInformationDto;
import ru.nabokovsg.dataservice.model.Application;
import ru.nabokovsg.dataservice.model.DocumentInformation;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DocumentInformationMapper {

    List<DocumentInformationDto> mapToDocumentInformationDto(List<DocumentInformation> reportData);

    DocumentInformation mapToDocumentInformation(Application application);
}