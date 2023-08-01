package ru.nabokovsg.dataservice.service;

import ru.nabokovsg.dataservice.dto.objectPassportDataTemplate.NewObjectPassportDataTemplateDto;
import ru.nabokovsg.dataservice.dto.objectPassportDataTemplate.ObjectPassportDataTemplateDto;
import ru.nabokovsg.dataservice.dto.objectPassportDataTemplate.UpdateObjectPassportDataTemplateDto;
import ru.nabokovsg.dataservice.model.ObjectPassportDataTemplate;

import java.util.List;
import java.util.Set;

public interface ObjectPassportDataTemplateService {

    List<ObjectPassportDataTemplateDto> save(List<NewObjectPassportDataTemplateDto> templatesDto);

   List<ObjectPassportDataTemplateDto> update(List<UpdateObjectPassportDataTemplateDto> templatesDto);

    Set<ObjectPassportDataTemplate> getAllByIds(List<Long> templatesIds);
}