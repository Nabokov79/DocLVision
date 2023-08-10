package ru.nabokovsg.dataservice.service;

import ru.nabokovsg.dataservice.dto.reportingDocument.NewReportingDocumentDto;
import ru.nabokovsg.dataservice.dto.reportingDocument.ReportingDocumentDto;
import ru.nabokovsg.dataservice.dto.reportingDocument.UpdateReportingDocumentDto;

import java.util.List;

public interface ReportingDocumentService {

    List<ReportingDocumentDto> save(List<NewReportingDocumentDto> reportingDocumentDto);

    List<ReportingDocumentDto> update(List<UpdateReportingDocumentDto> reportingDocumentDto);

    List<ReportingDocumentDto> getAll();

    void delete(Long id);
}