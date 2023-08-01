package ru.nabokovsg.dataservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.objectPassportDataTemplate.NewObjectPassportDataTemplateDto;
import ru.nabokovsg.dataservice.dto.objectPassportDataTemplate.ObjectPassportDataTemplateDto;
import ru.nabokovsg.dataservice.dto.objectPassportDataTemplate.UpdateObjectPassportDataTemplateDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mapper.ObjectPassportDataTemplateMapper;
import ru.nabokovsg.dataservice.model.ObjectPassportDataTemplate;
import ru.nabokovsg.dataservice.repository.ObjectPassportDataTemplateRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ObjectPassportDataTemplateServiceImpl implements ObjectPassportDataTemplateService {

    private final ObjectPassportDataTemplateRepository repository;
    private final ObjectPassportDataTemplateMapper mapper;

    @Override
    public List<ObjectPassportDataTemplateDto> save(List<NewObjectPassportDataTemplateDto> templatesDto) {
        return mapper.mapToObjectPassportDataTemplateDto(
                repository.saveAll(mapper.mapToNewObjectPassportDataTemplate(templatesDto))
        );
    }

    @Override
    public List<ObjectPassportDataTemplateDto> update(List<UpdateObjectPassportDataTemplateDto> templatesDto) {
        validateIds(templatesDto.stream().map(UpdateObjectPassportDataTemplateDto::getId).toList());
        return mapper.mapToObjectPassportDataTemplateDto(
                repository.saveAll(mapper.mapToUpdateObjectPassportDataTemplate(templatesDto))
        );
    }

    @Override
    public Set<ObjectPassportDataTemplate> getAllByIds(List<Long> templatesIds) {
        return new HashSet<>(repository.findAllById(templatesIds));
    }

    private void validateIds(List<Long> ids) {
        Map<Long, ObjectPassportDataTemplate> templates = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(ObjectPassportDataTemplate::getId, m -> m));
        if (templates.size() != ids.size() || templates.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(templates.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("templates with ids= %s not found", ids));
        }
    }
}
