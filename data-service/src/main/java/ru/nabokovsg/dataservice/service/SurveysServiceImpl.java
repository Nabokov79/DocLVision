package ru.nabokovsg.dataservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.model.Builder;
import ru.nabokovsg.dataservice.dto.survey.SurveyDto;
import ru.nabokovsg.dataservice.dto.survey.NewSurveyDto;
import ru.nabokovsg.dataservice.dto.survey.UpdateSurveyDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mapper.ObjectsIdsMapper;
import ru.nabokovsg.dataservice.mapper.SurveysMapper;
import ru.nabokovsg.dataservice.model.*;
import ru.nabokovsg.dataservice.repository.SurveysRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SurveysServiceImpl implements SurveysService {

    private final SurveysRepository repository;
    private final SurveysMapper mapper;
    private final PassportService passportService;
    private final ObjectsIdsMapper objectsIdsMapper;
    private final BuilderFactoryService factoryService;

    @Override
    public List<SurveyDto> save(List<NewSurveyDto> surveysDto) {
        Builder builder = factoryService.getBuilder(surveysDto.stream()
                                                              .map(objectsIdsMapper::mapFromNewSurveyDto)
                                                              .toList()
                                                  , BuilderType.SURVEY);
        Map<Long, Passport> passports = passportService.save(new ArrayList<>(builder.getObjects().values()))
                                                           .stream()
                                                           .collect(Collectors.toMap(p -> p.getObject().getId(), p -> p)
        );
        List<Survey> surveys = new ArrayList<>();
        for (NewSurveyDto surveyDto : surveysDto) {
            Survey survey = mapper.mapToNewSurvey(surveyDto);
            survey.setOrganization(builder.getOrganizations().get(surveyDto.getOrganizationId()));
            survey.setPassport(passports.get(surveyDto.getObjectId()));
            surveys.add(survey);
        }
        return mapper.mapToSurveyDto(repository.saveAll(surveys));
    }

    @Override
    public List<SurveyDto> update(List<UpdateSurveyDto> surveysDto) {
        validateIds(surveysDto.stream().map(UpdateSurveyDto::getId).toList());
        Builder builder = factoryService.getBuilder(surveysDto.stream()
                                                              .map(objectsIdsMapper::mapFromUpdateSurveyDto)
                                                              .toList()
                                                  , BuilderType.SURVEY);
        Map<Long, Passport> passports = passportService.getAll(new ArrayList<>(builder.getObjects().values()))
                                                        .stream()
                                                        .collect(Collectors.toMap(p -> p.getObject().getId(), p -> p));
        Map<Long, Survey> surveysDb = mapper.mapToUpdateSurvey(surveysDto)
                                                                      .stream()
                                                                      .collect(Collectors.toMap(Survey::getId, s -> s));
        for (UpdateSurveyDto surveyDto : surveysDto) {
            Survey survey = surveysDb.get(surveyDto.getId());
            survey.setOrganization(builder.getOrganizations().get(surveyDto.getOrganizationId()));
            survey.setPassport(passports.get(surveyDto.getObjectId()));
            surveysDb.put(survey.getId(), survey);
        }
        return mapper.mapToSurveyDto(repository.saveAll(new ArrayList<>(surveysDb.values())));
    }

    private void validateIds(List<Long> ids) {
        Map<Long, Survey> surveys = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(Survey::getId, m -> m));
        if (surveys.size() != ids.size() || surveys.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(surveys.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("surveys with ids= %s not found", ids));
        }
    }
}