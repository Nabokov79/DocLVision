package ru.nabokovsg.dataservice.dto.controlType;

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
@Schema(description = "Данные нового вида контроля")
public class NewControlTypeDto {

    @Schema(description = "Полное название вида контроля")
    @NotBlank(message = "name type control should not be blank")
    private String nameTypeControl;
    @Schema(description = "Краткое название вида контроля")
    @NotBlank(message = "short name type control should not be blank")
    private String shortNameTypeControl;
}