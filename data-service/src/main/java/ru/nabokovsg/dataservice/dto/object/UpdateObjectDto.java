package ru.nabokovsg.dataservice.dto.object;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для изменения данных объекта")
public class UpdateObjectDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id should not be blank")
    @Positive(message = "id must be positive")
    private Long id;
    @Schema(description = "Индентификатор типа объекта")
    @NotNull(message = "id organization's address must not be null")
    @Positive(message = "id organization's address must be negative")
    private Long objectsTypeId;
    @Schema(description = "Модель объекта")
    private String model;
    @Schema(description = "Объем объекта")
    private Integer number;
    @Schema(description = "Объем объекта")
    private Integer volume;
    @Schema(description = "Индентификатор строения")
    @NotNull(message = "id organization's address must not be null")
    @Positive(message = "id organization's address must be negative")
    private Long buildingId;
}