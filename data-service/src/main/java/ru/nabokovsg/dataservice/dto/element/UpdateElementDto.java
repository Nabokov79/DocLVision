package ru.nabokovsg.dataservice.dto.element;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.norms.UpdateNormsDto;
import ru.nabokovsg.dataservice.dto.place.UpdatePlaceDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения информации о элементе таблицы")
public class UpdateElementDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id should not be blank")
    @Positive(message = "id can only be positive")
    private Long id;
    @Schema(description = "Название элемента")
    @NotBlank(message = "element name should not be blank")
    private String element;
    @Schema(description = "Диаметр элемента")
    private Float diameter;
    @Schema(description = "Толщина элемента")
    @NotNull(message = "thickness should not be blank")
    @Positive(message = "thickness can only be positive")
    private Float thickness;
    @Schema(description = "Место замера на элементе")
    private List<UpdatePlaceDto> places;
    @Schema(description = "Список норм браковки элемента")
    @NotNull(message = "norms should not be blank")
    @NotEmpty(message = "norms can only be empty")
    private List<UpdateNormsDto> norms;
}