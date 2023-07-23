package ru.nabokovsg.dataservice.dto.city;

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
@Schema(description = "Данные для изменения информации о населенном пункте")
public class UpdateCityDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "city id should not be blank")
    @Positive(message = "city id can only be positive")
    private Long id;
    @Schema(description = "Название населенного пункта")
    @NotBlank(message = "name city should not be blank")
    private String name;
}
