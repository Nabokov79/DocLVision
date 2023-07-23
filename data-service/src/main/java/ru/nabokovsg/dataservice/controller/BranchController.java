package ru.nabokovsg.dataservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.dataservice.dto.branch.BranchDto;
import ru.nabokovsg.dataservice.dto.branch.NewBranchDto;
import ru.nabokovsg.dataservice.dto.branch.ShortBranchDto;
import ru.nabokovsg.dataservice.dto.branch.UpdateBranchDto;
import ru.nabokovsg.dataservice.service.BranchService;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping(
        value = "/data/branch",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Филиал организации",
        description="API для работы с данными филиала организации")
public class BranchController {

    private final BranchService service;

    @Operation(summary = "Добавление данных филиала")
    @PostMapping
    public ResponseEntity<BranchDto> save(@RequestBody @Parameter(description = "Филиал") NewBranchDto branchDto) {
        return ResponseEntity.ok().body(service.save(branchDto));
    }

    @Operation(summary = "Изменение данных филиала")
    @PatchMapping
    public ResponseEntity<BranchDto> update(@RequestBody @Parameter(description = "филиал") UpdateBranchDto branchDto) {
        return ResponseEntity.ok().body(service.update(branchDto));
    }

    @Operation(summary = "Получение данных падразделения")
    @GetMapping("/{id}")
    public ResponseEntity<BranchDto> get(@PathVariable @Parameter(description = "Индентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получение сведений о филиалах организации")
    @GetMapping
    public ResponseEntity<List<ShortBranchDto>> getAll(
          @RequestParam @NotNull @Positive @Parameter(description = "Индентификатор организации") Long organizationId) {
        return ResponseEntity.ok().body(service.getAll(organizationId));
    }

    @Operation(summary = "Удаление данных филиала организации")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(description = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("удалено");
    }
}