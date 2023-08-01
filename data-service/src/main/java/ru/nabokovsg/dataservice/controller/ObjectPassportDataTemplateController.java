package ru.nabokovsg.dataservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.dataservice.dto.objectPassportDataTemplate.NewObjectPassportDataTemplateDto;
import ru.nabokovsg.dataservice.dto.objectPassportDataTemplate.ObjectPassportDataTemplateDto;
import ru.nabokovsg.dataservice.dto.objectPassportDataTemplate.UpdateObjectPassportDataTemplateDto;
import ru.nabokovsg.dataservice.service.ObjectPassportDataTemplateService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/data/object/passport/data/template",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблон паспортных данных объекта",
        description="API для работы с данными шаблона паспортных данных объекта")
public class ObjectPassportDataTemplateController {

    private final ObjectPassportDataTemplateService service;

    @Operation(summary = "Добавление новых данных шаблона")
    @PostMapping
    public ResponseEntity<List<ObjectPassportDataTemplateDto>> save(
            @RequestBody
            @Parameter(description = "Данные шаблона") List<NewObjectPassportDataTemplateDto> templatesDto) {
        return ResponseEntity.ok().body(service.save(templatesDto));
    }

    @Operation(summary = "Изменение данных шаблона")
    @PatchMapping
    public ResponseEntity<List<ObjectPassportDataTemplateDto>> update(
            @RequestBody
            @Parameter(description = "Данные шаблона") List<UpdateObjectPassportDataTemplateDto> templatesDto) {
        return ResponseEntity.ok().body(service.update(templatesDto));
    }
}