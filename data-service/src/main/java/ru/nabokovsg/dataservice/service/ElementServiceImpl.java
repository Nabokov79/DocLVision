package ru.nabokovsg.dataservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.element.ElementDto;
import ru.nabokovsg.dataservice.dto.element.NewElementDto;
import ru.nabokovsg.dataservice.dto.element.UpdateElementDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mapper.ElementMapper;
import ru.nabokovsg.dataservice.model.Element;
import ru.nabokovsg.dataservice.repository.ElementRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ElementServiceImpl implements ElementService {

    private final ElementRepository repository;
    private final ElementMapper mapper;
    private final PlaceService placeService;
    private final NormsService normsService;

    @Override
    public List<ElementDto> save(List<NewElementDto> elementsDto) {
        List<Element> elements =new ArrayList<>();
        for (NewElementDto elementDto: elementsDto) {
            Element element = mapper.mapToNewElement(elementDto);
            if (elementDto.getPlaces() != null) {
                element.setPlaces(placeService.save(elementDto.getPlaces()));
            }
            element.setNorms(normsService.save(elementDto.getNorms()));
            elements.add(element);
        }
        return mapper.mapToElementsDto(repository.saveAll(elements));
    }

    @Override
    public List<ElementDto> update(List<UpdateElementDto> elementsDto) {
        validateIds(elementsDto.stream().map(UpdateElementDto::getId).toList());
        List<Element> elements =new ArrayList<>();
        for (UpdateElementDto elementDto: elementsDto) {
            Element element = mapper.mapToUpdateElements(elementDto);
            if (elementDto.getPlaces() != null) {
                element.setPlaces(placeService.update(elementDto.getPlaces()));
            }
            element.setNorms(normsService.update(elementDto.getNorms()));
            elements.add(element);
        }
        return mapper.mapToElementsDto(repository.saveAll(elements));
    }

    @Override
    public Set<Element> getAllByIds(List<Long> elementIds) {
        return new HashSet<>(repository.findAllById(elementIds));
    }

    private void validateIds(List<Long> ids) {
        Map<Long, Element> elements = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(Element::getId, n -> n));
        if (elements.size() != ids.size() || elements.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(elements.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("elements with ids= %s not found", ids));
        }
    }
}