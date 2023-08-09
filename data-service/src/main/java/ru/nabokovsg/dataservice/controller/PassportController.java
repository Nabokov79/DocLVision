package ru.nabokovsg.dataservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.dataservice.dto.passport.PassportDto;
import ru.nabokovsg.dataservice.service.PassportService;

@RestController
@RequestMapping(
        value = "/data/passport",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Паспорт объекта",
        description="API для работы с паспортом объекта")
public class PassportController {

    private final PassportService service;

    @Operation(summary = "Получение паспорта объекта")
    @GetMapping("/{id}")
    public ResponseEntity<PassportDto> save(@PathVariable @Parameter(description = "Индентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }
}
