package ru.nabokovsg.dataservice.mapper;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.objectPassportDataTemplate.NewObjectPassportDataTemplateDto;
import ru.nabokovsg.dataservice.dto.objectPassportDataTemplate.ObjectPassportDataTemplateDto;
import ru.nabokovsg.dataservice.dto.objectPassportDataTemplate.UpdateObjectPassportDataTemplateDto;
import ru.nabokovsg.dataservice.model.ObjectPassportDataTemplate;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ObjectPassportDataTemplateMapper {

    List<ObjectPassportDataTemplate> mapToNewObjectPassportDataTemplate(List<NewObjectPassportDataTemplateDto> templatesDto);

    List<ObjectPassportDataTemplate> mapToUpdateObjectPassportDataTemplate(List<UpdateObjectPassportDataTemplateDto> templatesDto);

    List<ObjectPassportDataTemplateDto> mapToObjectPassportDataTemplateDto(List<ObjectPassportDataTemplate> templates);
}