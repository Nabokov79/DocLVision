package ru.nabokovsg.dataservice.service;

import ru.nabokovsg.dataservice.dto.place.NewPlaceDto;
import ru.nabokovsg.dataservice.dto.place.UpdatePlaceDto;
import ru.nabokovsg.dataservice.model.Place;

import java.util.List;
import java.util.Set;

public interface PlaceService {

    Set<Place> save(List<NewPlaceDto> placesDto);

    Set<Place> update(List<UpdatePlaceDto> placesDto);
}