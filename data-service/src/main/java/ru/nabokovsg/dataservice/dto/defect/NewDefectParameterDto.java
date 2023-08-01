package ru.nabokovsg.dataservice.dto.defect;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные параметра дефекта")
public class NewDefectParameterDto {

    @Schema(description = "Название параметра дефекта")
    @NotBlank(message = "parameters name defect name should not be blank")
    private String parametersName;
}