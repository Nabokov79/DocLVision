package ru.nabokovsg.dataservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.dataservice.dto.employee.EmployeeDto;
import ru.nabokovsg.dataservice.dto.employee.ShortEmployeeDto;
import ru.nabokovsg.dataservice.dto.employee.UpdateEmployeeDto;
import ru.nabokovsg.dataservice.dto.employee.NewEmployeeDto;
import ru.nabokovsg.dataservice.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/data/employee",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Сотрудники",
     description="API для работы с данными сотрудников")
public class EmployeeController {

    private final EmployeeService service;

    @Operation(summary = "Добавление данных нового сотрудника")
    @PostMapping
    public ResponseEntity<ShortEmployeeDto> save(@RequestBody @Parameter(description = "Сотрудник") NewEmployeeDto employeeDto) {
        return ResponseEntity.ok().body(service.save(employeeDto));
    }

    @Operation(summary = "Изменение данных сотрудника")
    @PatchMapping
    public ResponseEntity<ShortEmployeeDto> update(@RequestBody @Parameter(description = "Сотрудник") UpdateEmployeeDto employeeDto) {
        return ResponseEntity.ok().body(service.update(employeeDto));
    }

    @Operation(summary = "Получение данных сотрудника")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> get(@PathVariable
                                       @Parameter(description = "Индентификатор сотрудника") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получение данных всех сотрудников")
    @GetMapping
    public ResponseEntity<List<ShortEmployeeDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @Operation(summary = "Удаление данных сотрудника")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable
                                         @Parameter(description = "Индентификатор сотрудника") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Данные сотрудника удалены.");
    }
}
