package ru.nabokovsg.dataservice.mapper;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.requisites.NewRequisitesDto;
import ru.nabokovsg.dataservice.dto.requisites.NewRequisitesEmployeeDto;
import ru.nabokovsg.dataservice.dto.requisites.UpdateRequisitesDto;
import ru.nabokovsg.dataservice.dto.requisites.UpdateRequisitesEmployeeDto;
import ru.nabokovsg.dataservice.model.Requisites;

@Mapper(componentModel = "spring")
public interface RequisitesMapper {

    Requisites mapToNewRequisites(NewRequisitesDto requisitesDto);

    Requisites mapToUpdateRequisites(UpdateRequisitesDto requisitesDto);

    NewRequisitesDto mapToNewRequisitesDto(NewRequisitesEmployeeDto requisitesDto);

    UpdateRequisitesDto mapToUpdateRequisitesDto(UpdateRequisitesEmployeeDto requisitesDto);
}