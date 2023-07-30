package ru.nabokovsg.dataservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.dataservice.dto.element.ElementDto;
import ru.nabokovsg.dataservice.dto.element.NewElementDto;
import ru.nabokovsg.dataservice.dto.element.UpdateElementDto;
import ru.nabokovsg.dataservice.service.ElementService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/data/elements",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Элемент объекта",
        description="API для работы с данными елемента объекта")
public class ElementController {

    private final ElementService service;

    @Operation(summary = "Добавление новоых элементов объекта")
    @PostMapping
    public ResponseEntity<List<ElementDto>> save(
            @RequestBody @Parameter(description = "Список элементов") List<NewElementDto> elementsDto) {
        return ResponseEntity.ok().body(service.save(elementsDto));
    }

    @Operation(summary = "Изменение данных элементов объекта")
    @PatchMapping
    public ResponseEntity<List<ElementDto>> update(
            @RequestBody @Parameter(description = "Список элементов") List<UpdateElementDto> elementsDto) {
        return ResponseEntity.ok().body(service.update(elementsDto));
    }
}