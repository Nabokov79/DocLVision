package ru.nabokovsg.dataservice.mapper;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.city.CityDto;
import ru.nabokovsg.dataservice.dto.city.NewCityDto;
import ru.nabokovsg.dataservice.dto.city.UpdateCityDto;
import ru.nabokovsg.dataservice.model.City;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CityMapper {

    City mapToNewCity(NewCityDto cityDto);

    CityDto mapToCityDto(City city);

    City mapToUpdateCity(UpdateCityDto cityDto);

    List<CityDto> mapToCityDto(List<City> cities);
}