package ru.nabokovsg.dataservice.dto.building;

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
@AllArgsConstructor
@NoArgsConstructor
public class NewBuildingDto {

    @Schema(description = "Тип строения")
    @NotBlank(message = "building should not be blank")
    private String buildingType;
    @Schema(description = "Название")
    private String login;
    @Schema(description = "Индентификатор подразделения филиала организации")
    @NotNull(message = "department id should not be blank")
    @Positive(message = "department id can only be positive")
    private Long departmentId;
    @Schema(description = "Реквизиты")
    private NewRequisitesDto requisites;
}