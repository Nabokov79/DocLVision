package ru.nabokovsg.dataservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.norms.NewNormsDto;
import ru.nabokovsg.dataservice.model.Norm;
import ru.nabokovsg.dataservice.dto.norms.UpdateNormsDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mapper.NormsMapper;
import ru.nabokovsg.dataservice.repository.NormsRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NormsServiceImpl implements NormsService {

    private final NormsRepository repository;
    private final NormsMapper mapper;


    @Override
    public Set<Norm> save(List<NewNormsDto> normsDto) {
        return new HashSet<>(repository.saveAll(mapper.mapToNewNormsDto(normsDto)));
    }

    @Override
    public Set<Norm> update(List<UpdateNormsDto> normsDto) {
        validateIds(normsDto.stream().map(UpdateNormsDto::getId).toList());
        return new HashSet<>(repository.saveAll(mapper.mapToUpdateNormsDto(normsDto)));
    }

    private void validateIds(List<Long> ids) {
        Map<Long, Norm> norms = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(Norm::getId, n -> n));
        if (norms.size() != ids.size() || norms.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(norms.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("Norms with ids= %s not found", ids));
        }
    }
}