package ru.nabokovsg.dataservice.dto.controlType;

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
@Schema(description = "Данные для изменения информации о виде контроля")
public class UpdateControlTypeDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id type control should not be blank")
    @Positive(message = "id type control must be positive")
    private Long id;
    @Schema(description = "Название")
    @NotBlank(message = "name type control should not be blank")
    private String nameTypeControl;
    @Schema(description = "Краткое название вида контроля")
    @NotBlank(message = "short name type control should not be blank")
    private String shortNameTypeControl;
}