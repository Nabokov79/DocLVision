package ru.nabokovsg.dataservice.service;

import ru.nabokovsg.dataservice.dto.survey.NewSurveyDto;
import ru.nabokovsg.dataservice.dto.survey.SurveyDto;
import ru.nabokovsg.dataservice.dto.survey.UpdateSurveyDto;

import java.util.List;

public interface SurveysService {

    List<SurveyDto> save(List<NewSurveyDto> surveysDto);

    List<SurveyDto> update(List<UpdateSurveyDto> surveysDto);
}