package ru.nabokovsg.dataservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.dataservice.dto.documentation.DocumentationDto;
import ru.nabokovsg.dataservice.dto.documentation.NewDocumentationDto;
import ru.nabokovsg.dataservice.dto.documentation.UpdateDocumentationDto;
import ru.nabokovsg.dataservice.service.DocumentationService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/data/documentations",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Нормативная документация",
     description="API для работы с данными нормативной документации")
public class DocumentationController {

    private final DocumentationService service;

    @Operation(summary = "Добавление нового нормативного документа")
    @PostMapping
    public ResponseEntity<List<DocumentationDto>> save(
               @RequestBody @Parameter(description = "Нормативный документ") List<NewDocumentationDto> documentations) {
        return ResponseEntity.ok().body(service.save(documentations));
    }

    @Operation(summary = "Изменение данных нормативного документа")
    @PatchMapping
    public ResponseEntity<List<DocumentationDto>> update(
            @RequestBody @Parameter(description = "Нормативный документ") List<UpdateDocumentationDto> documentations) {
        return ResponseEntity.ok().body(service.update(documentations));
    }
}