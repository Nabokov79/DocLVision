package ru.nabokovsg.dataservice.dto.defect;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные параметра дефекта")
public class DefectParameterDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Название параметра дефекта")
    private String parametersName;
}