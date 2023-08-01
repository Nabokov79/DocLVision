package ru.nabokovsg.dataservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.documentation.DocumentationDto;
import ru.nabokovsg.dataservice.dto.documentation.NewDocumentationDto;
import ru.nabokovsg.dataservice.dto.documentation.UpdateDocumentationDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mapper.DocumentationMapper;
import ru.nabokovsg.dataservice.model.Documentation;
import ru.nabokovsg.dataservice.repository.DocumentationRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentationServiceImpl implements DocumentationService {

    private final DocumentationRepository repository;
    private final DocumentationMapper mapper;

    @Override
    public List<DocumentationDto> save(List<NewDocumentationDto> documentationsDto) {
        Map<String, Documentation> documentations = mapper.mapToNewDocumentation(documentationsDto)
                                                   .stream().collect(Collectors.toMap(Documentation::getTitle, d -> d));
        Set<Documentation> documentationsDb = repository.findAllByTitle(documentationsDto
                                                                 .stream().map(NewDocumentationDto::getTitle).toList());
        List<Documentation> documentationDb = new ArrayList<>();
        if (documentationsDb != null && !documentationsDb.isEmpty()) {
            for (Documentation documentation : documentationsDb) {
                documentations.remove(documentation.getTitle());
            }
            documentationDb.addAll(documentationsDb);
        }
        documentationDb.addAll(repository.saveAll(new ArrayList<>(documentations.values())));
        return mapper.mapToDocumentationDto(documentationDb);
    }

    @Override
    public List<DocumentationDto> update(List<UpdateDocumentationDto> documentationsDto) {
        validateIds(documentationsDto.stream().map(UpdateDocumentationDto::getId).toList());
        return mapper.mapToDocumentationDto(repository.saveAll(mapper.mapToUpdateDocumentation(documentationsDto)));
    }

    @Override
    public Set<Documentation> getAllByIds(List<Long> documentationsIds) {
        return new HashSet<>(repository.findAllById(documentationsIds));
    }

    private void validateIds(List<Long> ids) {
        Map<Long, Documentation> documentations = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(Documentation::getId, subheading -> subheading));
        if (documentations.size() != ids.size() || documentations.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(documentations.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("documentations with ids= %s not found", ids));
        }
    }
}