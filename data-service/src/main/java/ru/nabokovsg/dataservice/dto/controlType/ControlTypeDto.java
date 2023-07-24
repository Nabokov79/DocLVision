package ru.nabokovsg.dataservice.dto.controlType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Вид контроля")
public class ControlTypeDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Полное название вида контроля")
    private String nameTypeControl;
    @Schema(description = "Краткое название вида контроля")
    private String shortNameTypeControl;
}