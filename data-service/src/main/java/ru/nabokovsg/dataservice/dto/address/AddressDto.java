package ru.nabokovsg.dataservice.dto.address;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.city.CityDto;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные адреса")
public class AddressDto {

    @Schema(description = "Индентификатор города")
    private Long id;
    @Schema(description = "Населенный пункт")
    private CityDto city;
    @Schema(description = "Название улицы")
    private String street;
    @Schema(description = "Номер дома")
    private Integer houseNumber;
    @Schema(description = "Номер корпуса дома")
    private Integer buildingNumber;
    @Schema(description = "Литера дома")
    private String letter;

    @Override
    public String toString() {
        return "AddressDto{" +
                "id=" + id +
                ", city=" + city +
                ", street='" + street + '\'' +
                ", houseNumber=" + houseNumber +
                ", buildingNumber=" + buildingNumber +
                ", letter='" + letter + '\'' +
                '}';
    }
}
