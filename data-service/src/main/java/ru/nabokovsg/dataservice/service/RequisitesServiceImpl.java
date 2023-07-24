package ru.nabokovsg.dataservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.requisites.NewRequisitesDto;
import ru.nabokovsg.dataservice.model.Requisites;
import ru.nabokovsg.dataservice.dto.requisites.UpdateRequisitesDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mapper.RequisitesMapper;
import ru.nabokovsg.dataservice.repository.RequisitesRepository;

@Service
@RequiredArgsConstructor
public class RequisitesServiceImpl implements RequisitesService {

    private final RequisitesRepository repository;
    private final RequisitesMapper mapper;
    private final AddressService addressService;

    @Override
    public Requisites save(NewRequisitesDto requisitesDto) {
        Requisites requisites = mapper.mapToNewRequisites(requisitesDto);
        requisites.setAddress(addressService.get(requisitesDto.getAddressId()));
        return repository.save(requisites);
    }

    @Override
    public Requisites update(UpdateRequisitesDto requisitesDto) {
        if (repository.existsById(requisitesDto.getId())) {
            Requisites requisites = mapper.mapToUpdateRequisites(requisitesDto);
            requisites.setAddress(addressService.get(requisitesDto.getAddressId()));
            return repository.save(requisites);
        }
        throw new NotFoundException(String.format("Requisites with id=%s not found for update", requisitesDto.getId()));
    }
}
