package ru.nabokovsg.dataservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.defect.NewDefectDto;
import ru.nabokovsg.dataservice.dto.defect.DefectDto;
import ru.nabokovsg.dataservice.dto.defect.UpdateDefectDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mapper.DefectMapper;
import ru.nabokovsg.dataservice.model.Defect;
import ru.nabokovsg.dataservice.repository.DefectRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefectsServiceImpl implements DefectsService {

    private final DefectRepository repository;
    private final DefectMapper mapper;
    private final DefectParameterService parameterService;

    @Override
    public List<DefectDto> save(List<NewDefectDto> defectsDto) {
        List<Defect> defects = new ArrayList<>();
        for (NewDefectDto defectDto: defectsDto) {
            Defect defect = new Defect();
            defect.setDefect(defectDto.getDefect());
            defect.setParameters(parameterService.save(defectDto.getParameters()));
            defects.add(defect);
        }
        return mapper.mapToDefectsDto(repository.saveAll(defects));
    }

    @Override
    public List<DefectDto> update(List<UpdateDefectDto> defectsDto) {
        validateIds(defectsDto.stream().map(UpdateDefectDto::getId).toList());
        List<Defect> defects = new ArrayList<>();
        for (UpdateDefectDto defectDto: defectsDto) {
            Defect defect = new Defect();
            defect.setDefect(defectDto.getDefect());
            defect.setParameters(parameterService.update(defectDto.getParameters()));
            defects.add(defect);
        }
        return mapper.mapToDefectsDto(repository.saveAll(defects));
    }

    @Override
    public Set<Defect> getAllByIds(List<Long> defectsIds) {
        return new HashSet<>(repository.findAllById(defectsIds));
    }

    private void validateIds(List<Long> ids) {
        Map<Long, Defect> defects = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(Defect::getId, d -> d));
        if (defects.size() != ids.size() || defects.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(defects.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("defects with ids= %s not found", ids));
        }
    }
}