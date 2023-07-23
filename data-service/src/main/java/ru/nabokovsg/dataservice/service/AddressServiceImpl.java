package ru.nabokovsg.dataservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.address.AddressDto;
import ru.nabokovsg.dataservice.dto.address.NewAddressDto;
import ru.nabokovsg.dataservice.dto.address.UpdateAddressDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mapper.AddressMapper;
import ru.nabokovsg.dataservice.model.Address;
import ru.nabokovsg.dataservice.repository.AddressRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository repository;
    private final AddressMapper mapper;
    private final CityService cityService;

    @Override
    public AddressDto save(NewAddressDto addressDto) {
        Address address = mapper.mapToNewAddress(addressDto);
        address.setCity(cityService.get(addressDto.getCityId()));
        return mapper.mapToAddressDto(repository.save(address));
    }

    @Override
    public AddressDto update(UpdateAddressDto addressDto) {
        if (!repository.existsById(addressDto.getId())) {
            throw new NotFoundException(String.format("address with id=%s not found for update.", addressDto.getId()));
        }
        Address address = mapper.mapToUpdateAddress(addressDto);
        address.setCity(cityService.get(addressDto.getCityId()));
        return mapper.mapToAddressDto(repository.save(address));
    }

    @Override
    public Address get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Address with id=%s not found", id)));
    }

    @Override
    public List<AddressDto> getAll(Long cityId, int from, int size) {
        Pageable pageable = PageRequest.of(from / size,size, Sort.by("cityId"));
        if(cityId != null) {
            return mapper.mapToAddressDto(new ArrayList<>(repository.findByCityId(cityId)));
        }
        return mapper.mapToAddressDto(repository.findAll(pageable).getContent());
    }

    @Override
    public String delete(Long id) {
        Address address = repository.findById(id).orElseThrow(() -> new NotFoundException(
                        String.format("address with id=%s not found for delete.", id)));
        repository.deleteById(id);
        return String.join(" ", address.getCity().getCity(),
                                                 address.getStreet(),
                                                 address.getHouseNumber().toString());
    }
}