package ru.nabokovsg.dataservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.dataservice.dto.reportingDocument.NewReportingDocumentDto;
import ru.nabokovsg.dataservice.dto.reportingDocument.ReportingDocumentDto;
import ru.nabokovsg.dataservice.dto.reportingDocument.UpdateReportingDocumentDto;
import ru.nabokovsg.dataservice.service.ReportingDocumentService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/data/applications/document",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Тип документа",
        description="API для работы с данными типа документа")
public class ReportingDocumentController {

    private final ReportingDocumentService service;

    @Operation(summary = "Добавление новых типов документов")
    @PostMapping
    public ResponseEntity<List<ReportingDocumentDto>> save(
            @RequestBody
            @Parameter(description = "Тип документа") List<NewReportingDocumentDto> reportingDocumentDto) {
        return ResponseEntity.ok().body(service.save(reportingDocumentDto));
    }

    @Operation(summary = "Изменение данных типов документа")
    @PatchMapping
    public ResponseEntity<List<ReportingDocumentDto>> update(
            @RequestBody
            @Parameter(description = "Тип документа") List<UpdateReportingDocumentDto> reportingDocumentDto) {
        return ResponseEntity.ok().body(service.update(reportingDocumentDto));
    }

    @Operation(summary = "Получение данных типов документов")
    @GetMapping
    public ResponseEntity<List<ReportingDocumentDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @Operation(summary = "Удаление типа документа")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(description = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("удалено");
    }
}