package ru.nabokovsg.dataservice.dto.objectsType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные типа объекта")
public class ObjectsTypeDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Название объекта")
    private String object;
    @Schema(description = "Положение объекта")
    private String orientation;
}