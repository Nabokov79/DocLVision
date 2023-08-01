package ru.nabokovsg.dataservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.defect.NewDefectParameterDto;
import ru.nabokovsg.dataservice.dto.defect.UpdateDefectParameterDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.model.DefectParameter;
import ru.nabokovsg.dataservice.mapper.DefectParameterMapper;
import ru.nabokovsg.dataservice.repository.DefectParameterRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefectParameterServiceImpl implements DefectParameterService {

    private final DefectParameterRepository repository;
    private final DefectParameterMapper mapper;

    @Override
    public Set<DefectParameter> save(List<NewDefectParameterDto> parametersDto) {
        Map<String, DefectParameter> parameters = repository.findDefectParameterByParametersName(
                                                                 (parametersDto.stream()
                                                                          .map(NewDefectParameterDto::getParametersName)
                                                                          .toList())
                                                        )
                                        .stream().collect(Collectors.toMap(DefectParameter::getParametersName, d -> d));
        if (parameters.isEmpty()) {
            return new HashSet<>(repository.saveAll(mapper.mapToNewDefectParameter(parametersDto)));
        } else if (parametersDto.size() == parameters.size()) {
            return new HashSet<>(parameters.values());
        } else {
            List<String> parametersNames = new ArrayList<>(parameters.keySet());
            parametersDto = parametersDto.stream()
                                         .filter(e -> !parametersNames.contains(e.getParametersName()))
                                         .collect(Collectors.toList());
            Set<DefectParameter> defectParameters = new HashSet<>(
                                                       repository.saveAll(mapper.mapToNewDefectParameter(parametersDto))
                                                );
            defectParameters.addAll(parameters.values());
            return defectParameters;
        }
    }

    @Override
    public Set<DefectParameter> update(List<UpdateDefectParameterDto> parametersDto) {
        validateIds(parametersDto.stream().map(UpdateDefectParameterDto::getId).toList());
        Map<String, DefectParameter> parameters = repository.findDefectParameterByParametersName(
                                                (parametersDto.stream()
                                                        .map(UpdateDefectParameterDto::getParametersName)
                                                        .toList())
                                        )
                                        .stream().collect(Collectors.toMap(DefectParameter::getParametersName, d -> d));
        if (parameters.isEmpty()) {
            return new HashSet<>(repository.saveAll(mapper.mapToUpdateDefectParameter(parametersDto)));
        } else if (parametersDto.size() == parameters.size()) {
            return new HashSet<>(parameters.values());
        } else {
            List<String> parametersNames = new ArrayList<>(parameters.keySet());
            parametersDto = parametersDto.stream()
                                         .filter(e -> !parametersNames.contains(e.getParametersName()))
                                         .collect(Collectors.toList());
            Set<DefectParameter> defectParameters = new HashSet<>(
                                                    repository.saveAll(mapper.mapToUpdateDefectParameter(parametersDto))
                                            );
            defectParameters.addAll(parameters.values());
            return defectParameters;
        }
    }

    private void validateIds(List<Long> ids) {
        Map<Long, DefectParameter> parameters = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(DefectParameter::getId, d -> d));
        if (parameters.size() != ids.size() || parameters.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(parameters.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("parameters with ids= %s not found", ids));
        }
    }
}