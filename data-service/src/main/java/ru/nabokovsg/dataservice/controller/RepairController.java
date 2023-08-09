package ru.nabokovsg.dataservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.dataservice.dto.repair.NewRepairDto;
import ru.nabokovsg.dataservice.dto.repair.RepairDto;
import ru.nabokovsg.dataservice.dto.repair.UpdateRepairDto;
import ru.nabokovsg.dataservice.service.RepairService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/data/passport/repairs",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Ремонт объекта",
        description="API для работы с данными ремонтов объекта")
public class RepairController {

    private final RepairService service;

    @Operation(summary = "Добавление нового данных ремонтов объектов")
    @PostMapping
    public ResponseEntity<List<RepairDto>> save(
            @RequestBody @Validated @Parameter(description = "Ремонты") List<NewRepairDto> repairsDto) {
        return ResponseEntity.ok().body(service.save(repairsDto));
    }

    @Operation(summary = "Изменение данных ремонтов объектов")
    @PatchMapping
    public ResponseEntity<List<RepairDto>> update(
            @RequestBody @Validated @Parameter(description = "Ремонты") List<UpdateRepairDto> repairsDto) {
        return ResponseEntity.ok().body(service.update(repairsDto));
    }
}
