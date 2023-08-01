package ru.nabokovsg.dataservice.mapper;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.defect.DefectDto;
import ru.nabokovsg.dataservice.model.Defect;
import java.util.List;

@Mapper(componentModel = "spring")
public interface DefectMapper {
    List<DefectDto> mapToDefectsDto(List<Defect> defects);
}