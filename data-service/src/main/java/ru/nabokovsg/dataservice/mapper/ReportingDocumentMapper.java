package ru.nabokovsg.dataservice.mapper;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.reportingDocument.NewReportingDocumentDto;
import ru.nabokovsg.dataservice.dto.reportingDocument.ReportingDocumentDto;
import ru.nabokovsg.dataservice.dto.reportingDocument.UpdateReportingDocumentDto;
import ru.nabokovsg.dataservice.model.ReportingDocument;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReportingDocumentMapper {

    List<ReportingDocument> mapFromNewReportingDocument(List<NewReportingDocumentDto> reportingDocumentDto);

    List<ReportingDocument> mapFromUpdateReportingDocument(List<UpdateReportingDocumentDto> reportingDocumentDto);

    List<ReportingDocumentDto> mapToReportingDocumentDto(List<ReportingDocument> reportingDocument);
}