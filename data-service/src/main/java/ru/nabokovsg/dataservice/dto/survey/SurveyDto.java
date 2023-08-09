package ru.nabokovsg.dataservice.dto.survey;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.organization.ShortOrganizationDto;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные обследования")
public class SurveyDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Дата обследования")
    private String date;
    @Schema(description = "Обследование")
    private String survey;
    @Schema(description = "Номер отчета обследования")
    private String surveyNumber;
    @Schema(description = "Организация, проводившая обследование")
    private ShortOrganizationDto organization;
}