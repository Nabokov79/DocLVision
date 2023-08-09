package ru.nabokovsg.dataservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.Builder;
import ru.nabokovsg.dataservice.dto.repair.NewRepairDto;
import ru.nabokovsg.dataservice.dto.repair.RepairDto;
import ru.nabokovsg.dataservice.dto.repair.UpdateRepairDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mapper.ObjectsIdsMapper;
import ru.nabokovsg.dataservice.mapper.RepairMapper;
import ru.nabokovsg.dataservice.model.*;
import ru.nabokovsg.dataservice.repository.RepairRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RepairServiceImpl implements RepairService {

    private final RepairRepository repository;
    private final RepairMapper mapper;
    private final ObjectsIdsMapper objectsIdsMapper;
    private final PassportService passportService;
    private final BuilderFactoryService factoryService;

    @Override
    public List<RepairDto> save(List<NewRepairDto> repairsDto) {
        Builder builder = factoryService.getBuilder(repairsDto.stream()
                                                              .map(objectsIdsMapper::mapFromNewRepairDto)
                                                              .toList()
                                                  , BuilderType.REPAIR);
        Map<Long, Passport> passports = passportService.save(new ArrayList<>(builder.getObjects().values()))
                .stream()
                .collect(Collectors.toMap(p -> p.getObject().getId(), p -> p)
                );
        List<Repair> repairs = new ArrayList<>();
        for (NewRepairDto repairDto : repairsDto) {
            Repair repair = mapper.mapFromNewRepair(repairDto);
            repair.setOrganization(builder.getOrganizations().get(repairDto.getOrganizationId()));
            repair.setPassport(passports.get(repairDto.getObjectId()));
            repairs.add(repair);
        }
        return mapper.mapToRepairDto(repository.saveAll(repairs));
    }

    @Override
    public List<RepairDto> update(List<UpdateRepairDto> repairsDto) {
        validateIds(repairsDto.stream().map(UpdateRepairDto::getId).toList());
        Builder builder = factoryService.getBuilder(repairsDto.stream()
                                                              .map(objectsIdsMapper::mapFromUpdateRepairDto)
                                                              .toList()
                                                  , BuilderType.REPAIR);
        Map<Long, Passport> passports = passportService.getAll(new ArrayList<>(builder.getObjects().values()))
                                                         .stream()
                                                         .collect(Collectors.toMap(p -> p.getObject().getId(), p -> p));
        Map<Long, Repair> repairsDb = mapper.mapFromUpdateRepair(repairsDto)
                                                                      .stream()
                                                                      .collect(Collectors.toMap(Repair::getId, r -> r));
        for (UpdateRepairDto repairDto : repairsDto) {
            Repair repair = repairsDb.get(repairDto.getId());
            repair.setOrganization(builder.getOrganizations().get(repairDto.getOrganizationId()));
            repair.setPassport(passports.get(repairDto.getObjectId()));
            repairsDb.put(repair.getId(), repair);
        }
        return mapper.mapToRepairDto(repository.saveAll(new ArrayList<>(repairsDb.values())));
    }

    private void validateIds(List<Long> ids) {
        Map<Long, Repair> repairs = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(Repair::getId, m -> m));
        if (repairs.size() != ids.size() || repairs.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(repairs.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("repairs with ids= %s not found", ids));
        }
    }
}