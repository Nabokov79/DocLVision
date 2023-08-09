package ru.nabokovsg.dataservice.service;

import ru.nabokovsg.dataservice.dto.dataPassportOfObject.DataPassportOfObjectDto;
import ru.nabokovsg.dataservice.dto.dataPassportOfObject.NewDataPassportOfObjectDto;
import ru.nabokovsg.dataservice.dto.dataPassportOfObject.UpdateDataPassportOfObjectDto;

import java.util.List;

public interface DataPassportOfObjectService {

    List<DataPassportOfObjectDto> save(List<NewDataPassportOfObjectDto> dataDto);

    List<DataPassportOfObjectDto> update(List<UpdateDataPassportOfObjectDto> dataDto);
}