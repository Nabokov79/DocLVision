package ru.nabokovsg.dataservice.dto.objectsTypeData;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.defect.DefectDto;
import ru.nabokovsg.dataservice.dto.documentation.DocumentationDto;
import ru.nabokovsg.dataservice.dto.element.ElementDto;
import ru.nabokovsg.dataservice.model.ObjectPassportDataTemplate;
import ru.nabokovsg.dataservice.model.ObjectsType;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные типа объекта")
public class ObjectsTypeDataDto {

    @Schema(description = "Тип объекта")
    private ObjectsType objectsType;
    @Schema(description = "Элементы типа объекта")
    private List<ElementDto> elements;
    @Schema(description = "Дефекты типа объекта")
    private List<DefectDto> defects;
    @Schema(description = "Нормативная документация к типу объекта")
    private List<DocumentationDto> documentations;
    @Schema(description = "Шаблон данных паспорта объекта")
    private List<ObjectPassportDataTemplate> passportDataTemplates;
}