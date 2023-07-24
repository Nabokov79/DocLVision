package ru.nabokovsg.dataservice.dto.organization;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.requisites.UpdateRequisitesDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для измененния информации о организации")
public class UpdateOrganizationDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id organization should not be blank")
    @Positive(message = "id organization must be positive")
    private Long id;
    @Schema(description = "Полное наименование организации")
    @NotBlank(message = "organization should not be blank")
    private String organization;
    @Schema(description = "Краткое наименование организации")
    @NotBlank(message = "short name organization should not be blank")
    private String shortNameOrganization;
    @Schema(description = "Реквизиты организации")
    private UpdateRequisitesDto requisites;
}