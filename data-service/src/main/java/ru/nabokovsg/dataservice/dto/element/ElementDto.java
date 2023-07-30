package ru.nabokovsg.dataservice.dto.element;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.norms.UpdateNormsDto;
import ru.nabokovsg.dataservice.dto.place.UpdatePlaceDto;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные элементе таблицы")
public class ElementDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Название элемента")
    private String element;
    @Schema(description = "Диаметр элемента")
    private Float diameter;
    @Schema(description = "Толщина элемента")
    private Float thickness;
    @Schema(description = "Место замера на элементе")
    private List<UpdatePlaceDto> places;
    @Schema(description = "Список норм браковки элемента")
    private List<UpdateNormsDto> norms;
}