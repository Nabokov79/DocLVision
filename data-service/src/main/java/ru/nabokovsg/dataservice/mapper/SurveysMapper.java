package ru.nabokovsg.dataservice.mapper;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.survey.NewSurveyDto;
import ru.nabokovsg.dataservice.dto.survey.SurveyDto;
import ru.nabokovsg.dataservice.dto.survey.UpdateSurveyDto;
import ru.nabokovsg.dataservice.model.Survey;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SurveysMapper {

    Survey mapToNewSurvey(NewSurveyDto surveyDto);

    Survey mapToUpdateSurvey(UpdateSurveyDto surveyDto);

    List<SurveyDto> mapToSurveyDto(List<Survey> surveys);
}