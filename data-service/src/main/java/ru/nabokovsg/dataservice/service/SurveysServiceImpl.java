package ru.nabokovsg.dataservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.Builder;
import ru.nabokovsg.dataservice.dto.ObjectsIds;
import ru.nabokovsg.dataservice.dto.survey.NewSurveyDto;
import ru.nabokovsg.dataservice.dto.survey.SurveyDto;
import ru.nabokovsg.dataservice.dto.survey.UpdateSurveyDto;
import ru.nabokovsg.dataservice.mapper.IdsMapper;
import ru.nabokovsg.dataservice.mapper.SurveysMapper;

import ru.nabokovsg.dataservice.model.BuilderType;
import ru.nabokovsg.dataservice.model.Survey;
import ru.nabokovsg.dataservice.repository.SurveysRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SurveysServiceImpl implements SurveysService {

    private final SurveysRepository repository;
    private final SurveysMapper mapper;
    private final BuilderService builderService;
    private final IdsMapper idsMapper;

    @Override
    public List<SurveyDto> save(List<NewSurveyDto> surveysDto) {
        Builder builder = getObjectsAndOrganization(surveysDto.stream().map(idsMapper::mapFromNewSurveyDto).toList());
        Map<Long, List<Survey>> surveyDb = new HashMap<>();
        for (Long objectId : builder.getObjects().keySet()) {
            if (surveyDb.get(objectId) == null) {
                List<Survey> surveys = new ArrayList<>();
                for (NewSurveyDto surveyDto : surveysDto.stream()
                                                        .filter(s -> s.getObjectId().equals(objectId))
                                                        .toList()) {
                    Survey survey = mapper.mapToNewSurvey(surveyDto);
                    survey.setOrganization(builder.getOrganizations().get(objectId));
                    surveys.add(survey);
                }
                surveyDb.put(objectId, repository.saveAll(surveys));
                surveys.clear();
            }
        }
        return null;
    }

    @Override
    public List<SurveyDto> update(List<UpdateSurveyDto> surveysDto) {
        Builder builder = getObjectsAndOrganization(surveysDto.stream().map(idsMapper::mapFromUpdateSurveyDto).toList());
        Map<Long, List<Survey>> surveyDb = new HashMap<>();
        for (Long objectId : builder.getObjects().keySet()) {
            if (surveyDb.get(objectId) == null) {
                List<Survey> surveys = new ArrayList<>();
                for (UpdateSurveyDto surveyDto : surveysDto.stream()
                        .filter(s -> s.getObjectId().equals(objectId))
                        .toList()) {
                    Survey survey = mapper.mapToUpdateSurvey(surveyDto);
                    survey.setOrganization(builder.getOrganizations().get(objectId));
                    surveys.add(survey);
                }
                surveyDb.put(objectId, repository.saveAll(surveys));
                surveys.clear();
            }
        }
        return null;
    }

    private Builder getObjectsAndOrganization(List<ObjectsIds> ids) {
        return builderService.getBuilder(ids, BuilderType.SURVEY);
    }
}
