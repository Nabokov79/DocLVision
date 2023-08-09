package ru.nabokovsg.dataservice.mapper;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.repair.NewRepairDto;
import ru.nabokovsg.dataservice.dto.repair.RepairDto;
import ru.nabokovsg.dataservice.dto.repair.UpdateRepairDto;
import ru.nabokovsg.dataservice.model.Repair;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RepairMapper {

    Repair mapFromNewRepair(NewRepairDto repairDto);

    List<Repair> mapFromUpdateRepair(List<UpdateRepairDto> repairsDto);
    List<RepairDto> mapToRepairDto(List<Repair> repairs);
}
