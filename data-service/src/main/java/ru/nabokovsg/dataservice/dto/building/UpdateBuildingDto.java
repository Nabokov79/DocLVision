package ru.nabokovsg.dataservice.dto.building;

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
public class UpdateBuildingDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id tank passport should not be blank")
    @Positive(message = "id tank passport must be positive")
    private Long id;
    @Schema(description = "Индентификатор адреса")
    @NotNull(message = "address id should not be blank")
    @Positive(message = "address id can only be positive")
    private Long addressId;
    @Schema(description = "Тип строения")
    @NotBlank(message = "building should not be blank")
    private String buildingType;
    @Schema(description = "Название")
    private String login;
    @Schema(description = "Индентификатор подразделения филиала организации")
    @NotNull(message = "department id should not be blank")
    @Positive(message = "department id can only be positive")
    private Long departmentId;
}
