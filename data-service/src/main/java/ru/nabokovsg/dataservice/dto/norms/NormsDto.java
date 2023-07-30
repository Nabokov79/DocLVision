package ru.nabokovsg.dataservice.dto.norms;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class NormsDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Предельно-минимальное допустимое значение в процентах от номинальной толщины элемента")
    private Long minInPercent;
    @Schema(description = "Предельно-минимальное допустимое значение в миллиметрах")
    private Float min;
    @Schema(description = "Значение допуска отклонения от номинального значения")
    private Float sizeTolerance;
    @Schema(description = "Погрешность измерения толщины элемента")
    private Float measurementError;
}