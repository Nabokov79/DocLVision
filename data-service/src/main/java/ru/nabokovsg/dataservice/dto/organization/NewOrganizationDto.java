package ru.nabokovsg.dataservice.dto.organization;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.requisites.NewRequisitesDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Данные новой организации")
public class NewOrganizationDto {

    @Schema(description = "Полное наименование организации")
    @NotBlank(message = "organization should not be blank")
    private String organization;
    @Schema(description = "Краткое наименование организации")
    @NotBlank(message = "short name organization should not be blank")
    private String shortNameOrganization;
    @Schema(description = "Индентификатор адреса организации")
    @NotNull(message = "id organization's address must not be null")
    @Positive(message = "id organization's address must be negative")
    private Long addressId;
    @Schema(description = "Реквизиты организации")
    private NewRequisitesDto requisites;
}