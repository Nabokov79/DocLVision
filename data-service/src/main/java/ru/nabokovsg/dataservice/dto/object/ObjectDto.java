package ru.nabokovsg.dataservice.dto.object;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.building.BuildingDto;
import ru.nabokovsg.dataservice.dto.objectsType.ObjectsTypeDto;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные объекта")
public class ObjectDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Тип объекта")
    private ObjectsTypeDto objectsType;
    @Schema(description = "Модель объекта")
    private String model;
    @Schema(description = "Объем объекта")
    private Integer number;
    @Schema(description = "Объем объекта")
    private Integer volume;
    @Schema(description = "Строение")
    private BuildingDto building;
}