package ru.nabokovsg.dataservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.dataservice.dto.defect.DefectDto;
import ru.nabokovsg.dataservice.dto.defect.NewDefectDto;
import ru.nabokovsg.dataservice.dto.defect.UpdateDefectDto;
import ru.nabokovsg.dataservice.service.DefectsService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/data/element/defects",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Дефекты элемента",
        description="API для работы с дефектами объекта")
public class DefectsController {

    private final DefectsService service;

    @Operation(summary = "Добавление новых дефектов объекта")
    @PostMapping
    public ResponseEntity<List<DefectDto>> save(@RequestBody @Parameter(description = "Дефекты") List<NewDefectDto> defectsDto) {
        return ResponseEntity.ok().body(service.save(defectsDto));
    }

    @Operation(summary = "Изменение данных дефектов объекта")
    @PatchMapping
    public ResponseEntity<List<DefectDto>> update(
            @RequestBody @Parameter(description = "Дефекты") List<UpdateDefectDto> defectsDto) {
        return ResponseEntity.ok().body(service.update(defectsDto));
    }
}