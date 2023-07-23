package ru.nabokovsg.dataservice.dto.city;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Данные нового населенного пункта")
public class NewCityDto {

    @Schema(description = "Название населенного пункта")
    @NotBlank(message = "name city should not be blank")
    private String city;
}
