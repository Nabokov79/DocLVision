package ru.nabokovsg.dataservice.service;

import ru.nabokovsg.dataservice.dto.city.CityDto;
import ru.nabokovsg.dataservice.dto.city.NewCityDto;
import ru.nabokovsg.dataservice.dto.city.UpdateCityDto;
import ru.nabokovsg.dataservice.model.City;

import java.util.List;

public interface CityService {

    CityDto save(NewCityDto cityDto);

    CityDto update(UpdateCityDto cityDto);

    City get(Long id);

    List<CityDto> getAll();

    String delete(Long id);
}