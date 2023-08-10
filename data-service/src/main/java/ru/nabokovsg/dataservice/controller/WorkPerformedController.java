package ru.nabokovsg.dataservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.dataservice.dto.workPerformed.NewWorkPerformedDto;
import ru.nabokovsg.dataservice.dto.workPerformed.UpdateWorkPerformedDto;
import ru.nabokovsg.dataservice.dto.workPerformed.WorkPerformedDto;
import ru.nabokovsg.dataservice.service.WorkPerformedService;
import java.util.List;

@RestController
@RequestMapping(
        value = "/data/applications/work",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Вид выполненных работ",
        description="API для работы с данными типов выполненных работ")
public class WorkPerformedController {

    private final WorkPerformedService service;

    @Operation(summary = "Добавление новых работ")
    @PostMapping
    public ResponseEntity<List<WorkPerformedDto>> save(
                        @RequestBody
                        @Parameter(description = "Вид выполненных работ") List<NewWorkPerformedDto> worksPerformedDto) {
        return ResponseEntity.ok().body(service.save(worksPerformedDto));
    }

    @Operation(summary = "Изменение данных работ")
    @PatchMapping
    public ResponseEntity<List<WorkPerformedDto>> update(
                     @RequestBody
                     @Parameter(description = "Вид выполненных работ") List<UpdateWorkPerformedDto> worksPerformedDto) {
        return ResponseEntity.ok().body(service.update(worksPerformedDto));
    }

    @Operation(summary = "Получение данных авторов проектов")
    @GetMapping
    public ResponseEntity<List<WorkPerformedDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @Operation(summary = "Удаление вида работы")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(description = "Индентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("удалено");
    }
}