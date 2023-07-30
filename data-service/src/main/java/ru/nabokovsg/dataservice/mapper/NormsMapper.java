package ru.nabokovsg.dataservice.mapper;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.norms.NewNormsDto;
import ru.nabokovsg.dataservice.dto.norms.UpdateNormsDto;
import ru.nabokovsg.dataservice.model.Norm;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NormsMapper {

    List<Norm> mapToNewNormsDto(List<NewNormsDto> normsDto);

    List<Norm> mapToUpdateNormsDto(List<UpdateNormsDto> normsDto);
}