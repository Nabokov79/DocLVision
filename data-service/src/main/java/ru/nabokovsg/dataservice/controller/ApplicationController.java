package ru.nabokovsg.dataservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.dataservice.dto.application.ApplicationDto;
import ru.nabokovsg.dataservice.dto.application.ApplicationSearchParameters;
import ru.nabokovsg.dataservice.dto.application.NewApplicationDto;
import ru.nabokovsg.dataservice.dto.application.UpdateApplicationDto;
import ru.nabokovsg.dataservice.service.ApplicationService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(
        value = "/data/applications",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Заявка",
        description="API для работы с данными заявки")
public class ApplicationController {

    private final ApplicationService service;

    @Operation(summary = "Добавление данных заявки")
    @PostMapping
    public ResponseEntity<List<ApplicationDto>> save(@RequestBody
                                               @Parameter(description = "Заявка")List<NewApplicationDto> applicationsDto) {
        return ResponseEntity.ok().body(service.save(applicationsDto));
    }

    @Operation(summary = "Изменение данных заявки")
    @PatchMapping
    public ResponseEntity<List<ApplicationDto>> update(@RequestBody
                                          @Parameter(description = "Заявка") List<UpdateApplicationDto> applicationsDto) {
        return ResponseEntity.ok().body(service.update(applicationsDto));
    }

    @Operation(summary = "Получение данных заявки")
    @GetMapping("/{id}")
    public ResponseEntity<ApplicationDto> get(@PathVariable @Parameter(description = "Индентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получение данных авторов проектов")
    @GetMapping
    public ResponseEntity<List<ApplicationDto>> getAll (
               @RequestParam(value = "addressId", required = false)
               @Parameter(description = "Индентификатор адреса") Long addressId,
               @RequestParam(value = "startDate", required = false)
               @Parameter(description = "Дата начала периода, за который требуется выдать заявки") LocalDate startDate,
               @RequestParam(value = "endDate", required = false)
               @Parameter(description = "Дата окончания периода, за который требуется выдать заявки") LocalDate endDate,
               @RequestParam(value = "objectId", required = false)
               @Parameter(description = "Индентификатор объекта оследования") Long objectId) {
        ApplicationSearchParameters parameters = new ApplicationSearchParameters(addressId, startDate
                                                                               , endDate, objectId);
        return ResponseEntity.ok().body(service.getAll(parameters));
    }
}