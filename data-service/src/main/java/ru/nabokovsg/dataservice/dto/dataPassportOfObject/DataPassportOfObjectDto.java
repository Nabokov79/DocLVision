package ru.nabokovsg.dataservice.dto.dataPassportOfObject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.objectPassportDataTemplate.ObjectPassportDataTemplateDto;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные паспорта объекта")
public class DataPassportOfObjectDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Шаблон данных паспорта объекта")
    private ObjectPassportDataTemplateDto template;
    @Schema(description = "Значение данных паспорта объекта")
    private String meaning;
}