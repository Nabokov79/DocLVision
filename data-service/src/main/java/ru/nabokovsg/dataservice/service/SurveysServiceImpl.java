package ru.nabokovsg.dataservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.survey.NewSurveyDto;
import ru.nabokovsg.dataservice.dto.survey.SurveyDto;
import ru.nabokovsg.dataservice.dto.survey.UpdateSurveyDto;
import ru.nabokovsg.dataservice.mapper.IdsMapper;
import ru.nabokovsg.dataservice.mapper.SurveysMapper;

import ru.nabokovsg.dataservice.model.BuilderType;
import ru.nabokovsg.dataservice.model.Objects;
import ru.nabokovsg.dataservice.model.Organization;
import ru.nabokovsg.dataservice.model.Survey;
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
    private final ObjectsService objectsService;
    private final BuilderService builderService;
    private final IdsMapper idsMapper;

    @Override
    public List<SurveyDto> save(List<NewSurveyDto> surveysDto) {
        Map<Long, Objects> objects = objectsService.getAllById(surveysDto.stream()
                                                                    .map(NewSurveyDto::getObjectId)
                                                                    .toList())
                                                                    .stream()
                                                                    .collect(Collectors.toMap(Objects::getId, o -> o));
        Map<Long, Organization> organizations = builderService.getBuilder(surveysDto
                                                        .stream()
                                                        .map(idsMapper::mapFromNewSurvey).toList(), BuilderType.LICENSE)
                                                        .getOrganizations();
        List<Survey> surveys = new ArrayList<>();
        for (NewSurveyDto surveyDto : surveysDto) {
            Survey survey = mapper.mapToNewSurvey(surveyDto);
            survey.setOrganization(organizations.get(surveyDto.getOrganizationId()));
            surveys.add(survey);
        }
        return mapper.mapToSurveyDto(repository.saveAll(surveys));
    }

    @Override
    public List<SurveyDto> update(List<UpdateSurveyDto> surveysDto) {
        Map<Long, Objects> objects = objectsService.getAllById(surveysDto.stream()
                                                                    .map(NewSurveyDto::getObjectId)
                                                                    .toList())
                                                                    .stream()
                                                                    .collect(Collectors.toMap(Objects::getId, o -> o));
        Map<Long, Organization> organizations = builderService.getBuilder(surveysDto.stream()
                                                     .map(idsMapper::mapFromUpdateSurvey).toList(), BuilderType.LICENSE)
                                                     .getOrganizations();
        List<Survey> surveys = new ArrayList<>();
        for (UpdateSurveyDto surveyDto : surveysDto) {
            Survey survey = mapper.mapToUpdateSurvey(surveyDto);
            survey.setOrganization(organizations.get(surveyDto.getOrganizationId()));
            surveys.add(survey);
        }
        return mapper.mapToSurveyDto(repository.saveAll(surveys));
    }
}
