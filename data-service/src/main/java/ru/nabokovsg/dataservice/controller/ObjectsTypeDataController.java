package ru.nabokovsg.dataservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.dataservice.dto.objectsTypeData.ObjectsTypeDataDto;
import ru.nabokovsg.dataservice.service.ObjectsTypeDataService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/data/object/type/data",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Данные объекта",
        description="API для работы с данными типа объекта")
public class ObjectsTypeDataController {

    private final ObjectsTypeDataService service;

    @Operation(summary = "Получить данные типа объекта")
    @GetMapping("/{id}")
    public ResponseEntity<ObjectsTypeDataDto> get(
            @PathVariable @Parameter(description = "Индентификатор данных типа объекта") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Добавление элементов к данным типа объекта")
    @PostMapping("/elements")
    public ResponseEntity<ObjectsTypeDataDto> addElements(
            @RequestParam @Parameter(description = "Индентификатор типа объекта") Long objectsTypeId,
            @RequestParam("elementIds")
            @Parameter(description = "Список индентификаторов элементов объекта") List<Long> elementIds) {
        return ResponseEntity.ok().body(service.addElements(objectsTypeId, elementIds));
    }

    @Operation(summary = "Изменение элементов, добавленных к данным типа объекта")
    @PatchMapping("/{id}/elements")
    public ResponseEntity<ObjectsTypeDataDto> updateElements(
            @PathVariable @Parameter(description = "Индентификатор данных типа объекта") Long id,
            @RequestParam("elementIds")
            @Parameter(description = "Список индентификаторов элементов объекта") List<Long> elementIds) {
        return ResponseEntity.ok().body(service.updateElements(id, elementIds));
    }

    @Operation(summary = "Добавление данных дефектов к данным типа объекта")
    @PostMapping("/defects")
    public ResponseEntity<ObjectsTypeDataDto> addDefects(
            @RequestParam @Parameter(description = "Индентификатор типа объекта") Long objectsTypeId,
            @RequestParam("defectsIds")
            @Parameter(description = "Список индентификаторов дефектов типа  объекта") List<Long> defectsIds) {
        return ResponseEntity.ok().body(service.addDefects(objectsTypeId, defectsIds));
    }

    @Operation(summary = "Изменение дефектов, добавленных к данным типа объекта")
    @PatchMapping("/{id}/defects")
    public ResponseEntity<ObjectsTypeDataDto> updateDefects(
            @PathVariable @Parameter(description = "Индентификатор данных типа объекта") Long id,
            @RequestParam("defectsIds")
            @Parameter(description = "Список индентификаторов дефектов типа  объекта") List<Long> defectsIds) {
        return ResponseEntity.ok().body(service.updateDefects(id, defectsIds));
    }

    @Operation(summary = "Добавление данных дефектов к данным типа объекта")
    @PostMapping("/documentations")
    public ResponseEntity<ObjectsTypeDataDto> addDocumentation(
            @RequestParam @Parameter(description = "Индентификатор типа объекта") Long objectsTypeId,
            @RequestParam("documentationIds")
            @Parameter(description = "Список индентификаторов документов для типа объекта")
                                                                                          List<Long> documentationIds) {
        return ResponseEntity.ok().body(service.addDocumentation(objectsTypeId, documentationIds));
    }

    @Operation(summary = "Изменение дефектов, добавленных к данным типа объекта")
    @PatchMapping("/{id}/documentations")
    public ResponseEntity<ObjectsTypeDataDto> updateDocumentation(
            @PathVariable @Parameter(description = "Индентификатор данных типа объекта") Long id,
            @RequestParam("documentationIds")
            @Parameter(description = "Список индентификаторов документов для типа объекта")
                                                                                          List<Long> documentationIds) {
        return ResponseEntity.ok().body(service.updateDocumentation(id, documentationIds));
    }
    @Operation(summary = "Добавление данных шаблона паспорта типа объекта")
    @PostMapping("/templates")
    public ResponseEntity<ObjectsTypeDataDto> addObjectPassportDataTemplate(
            @RequestParam @Parameter(description = "Индентификатор типа объекта") Long objectsTypeId,
            @RequestParam("templatesIds")
            @Parameter(description = "Список индентификаторов шаблонов паспортных данных объекта")
                                                                                              List<Long> templatesIds) {
        return ResponseEntity.ok().body(service.addObjectPassportDataTemplate(objectsTypeId, templatesIds));
    }

    @Operation(summary = "Изменение данных шаблона паспорта типа объекта")
    @PatchMapping("/{id}/templates")
    public ResponseEntity<ObjectsTypeDataDto> updateObjectPassportDataTemplate(
            @PathVariable @Parameter(description = "Индентификатор данных типа объекта") Long id,
            @RequestParam("templatesIds")
            @Parameter(description = "Список индентификаторов шаблонов паспортных данных объекта")
                                                                                              List<Long> templatesIds) {
        return ResponseEntity.ok().body(service.updateObjectPassportDataTemplate(id, templatesIds));
    }
}