package ru.nabokovsg.dataservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.dataservice.dto.department.DepartmentDto;
import ru.nabokovsg.dataservice.dto.department.NewDepartmentDto;
import ru.nabokovsg.dataservice.dto.department.ShortDepartmentDto;
import ru.nabokovsg.dataservice.dto.department.UpdateDepartmentDto;
import ru.nabokovsg.dataservice.service.DepartmentService;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping(
        value = "/data/department",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Подразделение филиала организации",
        description="API для работы с данными подразделения филиала организации")
public class DepartmentController {

    private final DepartmentService service;

    @Operation(summary = "Добавление данных подразделения филиала организации")
    @PostMapping
    public ResponseEntity<DepartmentDto> save(
                                       @RequestBody @Parameter(description = "Филиал") NewDepartmentDto departmentDto) {
        return ResponseEntity.ok().body(service.save(departmentDto));
    }

    @Operation(summary = "Изменение данных подразделения филиала организации")
    @PatchMapping
    public ResponseEntity<DepartmentDto> update(
                                    @RequestBody @Parameter(description = "филиал") UpdateDepartmentDto departmentDto) {
        return ResponseEntity.ok().body(service.update(departmentDto));
    }

    @Operation(summary = "Получение данных пподразделения филиала организации")
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> get(@PathVariable @Parameter(description = "Индентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получение кратких сведений подразделений филиала организации")
    @GetMapping
    public ResponseEntity<List<ShortDepartmentDto>> getAll(
                                        @RequestParam @NotNull @Positive
                                        @Parameter(description = "Индентификатор филиала организации") Long branchId) {
        return ResponseEntity.ok().body(service.getAll(branchId));
    }

    @Operation(summary = "Удаление данных подразделения филиала организации")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(description = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("удалено");
    }
}