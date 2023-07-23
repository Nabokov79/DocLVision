package ru.nabokovsg.dataservice.dto.city;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные населенного пункта")
public class CityDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Название населенного пункта")
    private String city;

    @Override
    public String toString() {
        return "CityDto{" +
                "id=" + id +
                ", city='" + city + '\'' +
                '}';
    }
}
