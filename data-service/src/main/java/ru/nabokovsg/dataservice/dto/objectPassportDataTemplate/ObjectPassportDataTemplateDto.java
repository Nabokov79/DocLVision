package ru.nabokovsg.dataservice.dto.objectPassportDataTemplate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения шаблона паспортных данных объекта")
public class ObjectPassportDataTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Индентификатор типа объекта")
    private Float dataSequenceNumber;
    @Schema(description = "Индентификатор типа объекта")
    private String dataName;
}