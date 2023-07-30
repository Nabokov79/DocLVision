package ru.nabokovsg.dataservice.dto.place;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные наименования места замера")
public class PlaceDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Наименование места замера на элементе")
    private String place;
}
