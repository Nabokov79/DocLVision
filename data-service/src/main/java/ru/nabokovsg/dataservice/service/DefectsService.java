package ru.nabokovsg.dataservice.service;

import ru.nabokovsg.dataservice.dto.defect.DefectDto;
import ru.nabokovsg.dataservice.dto.defect.NewDefectDto;
import ru.nabokovsg.dataservice.dto.defect.UpdateDefectDto;
import ru.nabokovsg.dataservice.model.Defect;

import java.util.List;
import java.util.Set;

public interface DefectsService {

    List<DefectDto> save(List<NewDefectDto> defects);

    List<DefectDto> update(List<UpdateDefectDto> defects);

    Set<Defect> getAllByIds(List<Long> defectsIds);
}