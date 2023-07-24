package ru.nabokovsg.dataservice.dto.building;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.department.ShortDepartmentDto;
import ru.nabokovsg.dataservice.dto.requisites.RequisitesDto;

@Setter
@Getter
@AllArgsConstructor
public class BuildingDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Тип строения")
    private String buildingType;
    @Schema(description = "Название")
    private String login;
    @Schema(description = "Подразделение филиала организации")
    private ShortDepartmentDto department;
    @Schema(description = "Реквизиты")
    private RequisitesDto requisites;
}
