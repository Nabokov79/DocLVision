package ru.nabokovsg.dataservice.dto.defect;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные дефекта")
public class DefectDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Название дефекта")
    @NotBlank(message = "defect name should not be blank")
    private String defect;
    @Schema(description = "Параметры дефекта")
    @NotNull(message = "parameters list should not be null")
    @NotEmpty(message = "parameters list can only be empty")
    private List<DefectParameterDto> parameters;
}