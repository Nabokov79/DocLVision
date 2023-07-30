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
@Schema(description = "Данные нового объекта")
public class NewObjectDto {

    @Schema(description = "Индентификатор типа объекта")
    @NotNull(message = "id object must not be null")
    @Positive(message = "id object must be negative")
    private Long objectsTypeId;
    @Schema(description = "Модель объекта")
    private String model;
    @Schema(description = "Объем объекта")
    private Integer number;
    @Schema(description = "Объем объекта")
    private Integer volume;
    @Schema(description = "Индентификатор строения")
    @NotNull(message = "id building must not be null")
    @Positive(message = "id building address must be negative")
    private Long buildingId;
}