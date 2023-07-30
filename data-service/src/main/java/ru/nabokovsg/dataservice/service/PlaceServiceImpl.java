package ru.nabokovsg.dataservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.place.NewPlaceDto;
import ru.nabokovsg.dataservice.dto.place.UpdatePlaceDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mapper.PlaceMapper;
import ru.nabokovsg.dataservice.model.Place;
import ru.nabokovsg.dataservice.repository.PlaceRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository repository;
    private final PlaceMapper mapper;

    @Override
    public Set<Place> save(List<NewPlaceDto> placesDto) {
        return new HashSet<>(repository.saveAll(mapper.mapToNewPlace(placesDto)));
    }

    @Override
    public Set<Place> update(List<UpdatePlaceDto> placesDto) {
        validateIds(placesDto.stream().map(UpdatePlaceDto::getId).toList());
        return new HashSet<>(repository.saveAll(mapper.mapToUpdatePlace(placesDto)));
    }

    private void validateIds(List<Long> ids) {
        Map<Long, Place> places = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(Place::getId, d -> d));
        if (places.size() != ids.size() || places.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(places.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("places with ids= %s not found", ids));
        }
    }
}