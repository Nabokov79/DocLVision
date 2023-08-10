package ru.nabokovsg.dataservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nabokovsg.dataservice.dto.reportData.DocumentInformationDto;
import ru.nabokovsg.dataservice.dto.reportData.DocumentSearchParameters;
import ru.nabokovsg.dataservice.service.DocumentInformationService;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(
        value = "/data/document/information",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Отчетная документация",
        description="API для работы с данными отчетной документации")
public class DocumentInformationController {

    private final DocumentInformationService service;

    @Operation(summary = "Получение данных отчетных документов")
    @GetMapping
    public ResponseEntity<List<DocumentInformationDto>> getAll(
         @RequestParam(value = "objectDataId", required = false)
         @NotNull @Positive @Parameter(description = "Объект обследования") Long objectDataId,
         @RequestParam(value = "employeeId", required = false)
         @NotNull @Positive @Parameter(description = "Сотрудник") Long employeeId,
         @RequestParam(value = "addressId", required = false)
         @NotNull @Positive @Parameter(description = "Адрес") Long addressId,
         @RequestParam(value = "documentNumber", required = false)
         @NotNull @Positive @Parameter(description = "Номер документа") Integer documentNumber,
         @RequestParam(value = "status", required = false)
         @NotNull @Parameter(description = "Статус документа") String status,
         @RequestParam(value = "startDate", required = false)
         @NotNull
         @Parameter(description = "Дата начала периода, за который требуется выдать отчеты") LocalDateTime startDate,
         @RequestParam(value = "endDate", required = false)
         @NotNull
         @Parameter(description = "Дата окончания периода, за который требуется выдать отчеты") LocalDateTime endDate) {
        DocumentSearchParameters parameters = new DocumentSearchParameters(objectDataId, employeeId, addressId
                                                                         , documentNumber, status, startDate, endDate);
        return ResponseEntity.ok().body(service.getAll(parameters));
    }
}