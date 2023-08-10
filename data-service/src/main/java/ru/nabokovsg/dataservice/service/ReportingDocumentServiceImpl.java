package ru.nabokovsg.dataservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.reportingDocument.NewReportingDocumentDto;
import ru.nabokovsg.dataservice.dto.reportingDocument.ReportingDocumentDto;
import ru.nabokovsg.dataservice.dto.reportingDocument.UpdateReportingDocumentDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mapper.ReportingDocumentMapper;
import ru.nabokovsg.dataservice.model.ReportingDocument;
import ru.nabokovsg.dataservice.repository.ReportingDocumentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportingDocumentServiceImpl implements ReportingDocumentService {

    private final ReportingDocumentRepository repository;
    private final ReportingDocumentMapper mapper;

    @Override
    public List<ReportingDocumentDto> save(List<NewReportingDocumentDto> reportingDocumentDto) {
        return mapper.mapToReportingDocumentDto(
                repository.saveAll(mapper.mapFromNewReportingDocument(reportingDocumentDto))
        );
    }

    @Override
    public List<ReportingDocumentDto> update(List<UpdateReportingDocumentDto> reportingDocumentDto) {
        validateIds(reportingDocumentDto.stream().map(UpdateReportingDocumentDto::getId).toList());
        return mapper.mapToReportingDocumentDto(
                repository.saveAll(mapper.mapFromUpdateReportingDocument(reportingDocumentDto))
        );
    }

    @Override
    public List<ReportingDocumentDto> getAll() {
        return mapper.mapToReportingDocumentDto(repository.findAll());
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("reporting document with id=%s not found for delete", id));
    }

    private void validateIds(List<Long> ids) {
        Map<Long, ReportingDocument> documents = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(ReportingDocument::getId, m -> m));
        if (documents.size() != ids.size() || documents.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(documents.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("reporting document with ids= %s not found", ids));
        }
    }
}