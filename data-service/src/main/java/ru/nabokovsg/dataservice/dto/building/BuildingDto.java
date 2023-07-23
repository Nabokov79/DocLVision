package ru.nabokovsg.dataservice.dto.building;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.address.AddressDto;
import ru.nabokovsg.dataservice.dto.department.ShortDepartmentDto;

@Setter
@Getter
@AllArgsConstructor
public class BuildingDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Адрес")
    private AddressDto address;
    @Schema(description = "Тип строения")
    private String buildingType;
    @Schema(description = "Название")
    private String login;
    @Schema(description = "Подразделение филиала организации")
    private ShortDepartmentDto department;
}
