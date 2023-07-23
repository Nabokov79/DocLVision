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

    @Override
    public Requisites save(NewRequisitesDto requisitesDto) {
        return repository.save(mapper.mapToNewRequisites(requisitesDto));
    }

    @Override
    public Requisites update(UpdateRequisitesDto requisitesDto) {
        if (repository.existsById(requisitesDto.getId())) {
            return repository.save(mapper.mapToUpdateRequisites(requisitesDto));

        }
        throw new NotFoundException(String.format("Requisites with id=%s not found for update", requisitesDto.getId()));
    }
}
