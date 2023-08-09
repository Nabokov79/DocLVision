package ru.nabokovsg.dataservice.service;

import ru.nabokovsg.dataservice.dto.repair.NewRepairDto;
import ru.nabokovsg.dataservice.dto.repair.RepairDto;
import ru.nabokovsg.dataservice.dto.repair.UpdateRepairDto;

import java.util.List;

public interface RepairService {

    List<RepairDto> save(List<NewRepairDto> repairsDto);

    List<RepairDto> update(List<UpdateRepairDto> repairsDto);
}
