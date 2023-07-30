package ru.nabokovsg.dataservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.dataservice.dto.object.NewObjectDto;
import ru.nabokovsg.dataservice.dto.object.ObjectDto;
import ru.nabokovsg.dataservice.dto.object.UpdateObjectDto;
import ru.nabokovsg.dataservice.service.ObjectsService;
import java.util.List;

@RestController
@RequestMapping(
        value = "/data/objects",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Объект",
        description="API для работы с данными объекта")
public class ObjectsController {

    private final ObjectsService service;

    @Operation(summary = "Добавление данных типа объекта")
    @PostMapping
    public ResponseEntity<List<ObjectDto>> save(@RequestBody
                                                @Parameter(description = "Тип объекта") List<NewObjectDto> objectDto ) {
        return ResponseEntity.ok().body(service.save(objectDto));
    }

    @Operation(summary = "Изменение данных типа объекта")
    @PatchMapping
    public ResponseEntity<List<ObjectDto>> update(@RequestBody
                                              @Parameter(description = "Тип объекта") List<UpdateObjectDto> objectDto) {
        return ResponseEntity.ok().body(service.update(objectDto));
    }

    @Operation(summary = "Удаление объекта")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable
                                         @Parameter(description = "Индентификатор объекта") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Тип объекта удален.");
    }
}