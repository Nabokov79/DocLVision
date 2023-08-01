package ru.nabokovsg.dataservice.dto.survey;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения информации об обследовании")
public class UpdateSurveyDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id survey should not be blank")
    @Positive(message = "id survey must be positive")
    private Long id;
    @Schema(description = "Индентификатор объекта обследования ")
    @NotNull(message = "id object data should not be blank")
    @Positive(message = "id object data can only be positive")
    private Long objectId;
    @Schema(description = "Дата обследования")
    @NotNull(message = "date repair should not be blank")
    private String date;
    @Schema(description = "Обследование")
    @NotNull(message = "survey should not be blank")
    private String survey;
    @Schema(description = "Номер отчета обследования")
    @NotBlank(message = "number should not be blank")
    private String surveyNumber;
    @Schema(description = "Индентификатор организации, проводившей обследование")
    @NotNull(message = "organization id should not be blank")
    private Long organizationId;
}
