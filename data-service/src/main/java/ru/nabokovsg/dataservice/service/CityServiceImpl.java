package ru.nabokovsg.dataservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.city.CityDto;
import ru.nabokovsg.dataservice.dto.city.NewCityDto;
import ru.nabokovsg.dataservice.dto.city.UpdateCityDto;
import ru.nabokovsg.dataservice.exceptions.BadRequestException;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mapper.CityMapper;
import ru.nabokovsg.dataservice.model.City;
import ru.nabokovsg.dataservice.repository.CityRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository repository;
    private final CityMapper mapper;

    @Override
    public CityDto save(NewCityDto cityDto) {
        if (repository.existsByCity(cityDto.getCity())) {
            throw new BadRequestException(String.format("city=%s found.", cityDto.getCity()));
        }
        return mapper.mapToCityDto(repository.save(mapper.mapToNewCity(cityDto)));
    }

    @Override
    public CityDto update(UpdateCityDto cityDto) {
        if (!repository.existsById(cityDto.getId())) {
            throw new NotFoundException(String.format("city with id=%s not found for update.", cityDto.getId()));
        }
        return mapper.mapToCityDto(repository.save(mapper.mapToUpdateCity(cityDto)));
    }

    @Override
    public City get(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(
                                                               String.format("city with id=%s not found for set", id)));
    }

    @Override
    public List<CityDto> getAll() {
        return mapper.mapToCityDto(repository.findAll());
    }

    @Override
    public String delete(Long id) {
        City city = repository.findById(id).orElseThrow(() -> new NotFoundException(
                                                  String.format("city with id=%s not found for delete.", id)));
        repository.deleteById(id);
        return city.getCity();
    }
}