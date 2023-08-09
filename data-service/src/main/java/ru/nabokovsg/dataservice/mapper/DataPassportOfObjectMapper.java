package ru.nabokovsg.dataservice.mapper;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.dataPassportOfObject.DataPassportOfObjectDto;
import ru.nabokovsg.dataservice.dto.dataPassportOfObject.NewDataPassportOfObjectDto;
import ru.nabokovsg.dataservice.dto.dataPassportOfObject.UpdateDataPassportOfObjectDto;
import ru.nabokovsg.dataservice.model.DataPassportOfObject;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DataPassportOfObjectMapper {

    DataPassportOfObject mapToNewDataPassportOfObject(NewDataPassportOfObjectDto dataPassportDto);

    List<DataPassportOfObject> mapToUpdateDataPassportOfObject(List<UpdateDataPassportOfObjectDto> dataPassportDto);

    List<DataPassportOfObjectDto> mapToDataPassportOfObjectDto(List<DataPassportOfObject> dataPassport);
}