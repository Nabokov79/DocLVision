package ru.nabokovsg.dataservice.mapper;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.place.NewPlaceDto;
import ru.nabokovsg.dataservice.dto.place.UpdatePlaceDto;
import ru.nabokovsg.dataservice.model.Place;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlaceMapper {

    List<Place> mapToNewPlace(List<NewPlaceDto> placesDto);

    List<Place> mapToUpdatePlace(List<UpdatePlaceDto> placesDto);
}