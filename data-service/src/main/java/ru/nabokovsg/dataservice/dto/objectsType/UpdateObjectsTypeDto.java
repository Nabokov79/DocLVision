package ru.nabokovsg.dataservice.dto.objectsType;

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
@Schema(description = "Данные для изменения информации о типе объекта")
public class UpdateObjectsTypeDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id should not be blank")
    @Positive(message = "id must be positive")
    private Long id;
    @Schema(description = "Название объекта")
    @NotBlank(message = "name should not be blank")
    private String object;
    @Schema(description = "Положение объекта")
    private String orientation;
}