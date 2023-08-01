package ru.nabokovsg.dataservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.dataservice.dto.survey.NewSurveyDto;
import ru.nabokovsg.dataservice.dto.survey.SurveyDto;
import ru.nabokovsg.dataservice.dto.survey.UpdateSurveyDto;
import ru.nabokovsg.dataservice.service.SurveysService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/data/passport/surveys",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Обследование объекта",
        description="API для работы с данными обследований объекта")
public class SurveysController {

    private final SurveysService service;

    @Operation(summary = "Добавление нового обследования")
    @PostMapping
    public ResponseEntity<List<SurveyDto>> save(
            @RequestBody @Validated @Parameter(description = "Обследования") List<NewSurveyDto> surveysDto) {
        return ResponseEntity.ok().body(service.save(surveysDto));
    }

    @Operation(summary = "Изменение данных адреса")
    @PatchMapping
    public ResponseEntity<List<SurveyDto>> update(
            @RequestBody @Validated @Parameter(description = "Обследования") List<UpdateSurveyDto> surveysDto) {
        return ResponseEntity.ok().body(service.update(surveysDto));
    }
}
