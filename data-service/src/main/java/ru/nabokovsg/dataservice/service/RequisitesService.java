package ru.nabokovsg.dataservice.service;

import ru.nabokovsg.dataservice.dto.requisites.NewRequisitesDto;
import ru.nabokovsg.dataservice.dto.requisites.UpdateRequisitesDto;
import ru.nabokovsg.dataservice.model.Requisites;

public interface RequisitesService {

    Requisites save(NewRequisitesDto requisitesDto);

    Requisites update(UpdateRequisitesDto requisitesDto);
}
