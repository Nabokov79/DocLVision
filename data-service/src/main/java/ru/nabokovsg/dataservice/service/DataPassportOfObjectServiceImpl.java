package ru.nabokovsg.dataservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.model.Builder;
import ru.nabokovsg.dataservice.dto.dataPassportOfObject.DataPassportOfObjectDto;
import ru.nabokovsg.dataservice.dto.dataPassportOfObject.NewDataPassportOfObjectDto;
import ru.nabokovsg.dataservice.dto.dataPassportOfObject.UpdateDataPassportOfObjectDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mapper.DataPassportOfObjectMapper;
import ru.nabokovsg.dataservice.mapper.ObjectsIdsMapper;
import ru.nabokovsg.dataservice.model.BuilderType;
import ru.nabokovsg.dataservice.model.DataPassportOfObject;
import ru.nabokovsg.dataservice.model.Passport;
import ru.nabokovsg.dataservice.repository.DataPassportOfObjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DataPassportOfObjectServiceImpl implements DataPassportOfObjectService {

    private final DataPassportOfObjectRepository repository;
    private final DataPassportOfObjectMapper mapper;
    private final BuilderFactoryService factoryService;
    private final ObjectsIdsMapper objectsIdsMapper;
    private final PassportService passportService;

    @Override
    public List<DataPassportOfObjectDto> save(List<NewDataPassportOfObjectDto> dataDto) {
        Builder builder = factoryService.getBuilder(dataDto.stream()
                                                           .map(objectsIdsMapper::mapFromNewDataPassportOfObjectDto)
                                                           .toList()
                                                  , BuilderType.PASSPORT_DATA);
        Map<Long, Passport> passports = passportService.save(new ArrayList<>(builder.getObjects().values()))
                .stream()
                .collect(Collectors.toMap(p -> p.getObject().getId(), p -> p)
                );
        List<DataPassportOfObject> data = new ArrayList<>();
        for (NewDataPassportOfObjectDto dataPassportDto : dataDto) {
            DataPassportOfObject dataPassport = mapper.mapToNewDataPassportOfObject(dataPassportDto);
            dataPassport.setTemplate(builder.getTemplates().get(dataPassportDto.getTemplateId()));
            dataPassport.setPassport(passports.get(dataPassportDto.getObjectId()));
            data.add(dataPassport);
        }
        return mapper.mapToDataPassportOfObjectDto(repository.saveAll(data));
    }

    @Override
    public List<DataPassportOfObjectDto> update(List<UpdateDataPassportOfObjectDto> dataDto) {
        validateIds(dataDto.stream().map(UpdateDataPassportOfObjectDto::getId).toList());
        Builder builder = factoryService.getBuilder(dataDto.stream()
                                                           .map(objectsIdsMapper::mapFromUpdateDataPassportOfObjectDto)
                                                           .toList()
                                                  , BuilderType.PASSPORT_DATA);
        Map<Long, Passport> passports = passportService.getAll(new ArrayList<>(builder.getObjects().values()))
                                                        .stream()
                                                        .collect(Collectors.toMap(p -> p.getObject().getId(), p -> p));
        Map<Long, DataPassportOfObject> dataPassportDb = mapper.mapToUpdateDataPassportOfObject(dataDto)
                                                        .stream()
                                                        .collect(Collectors.toMap(DataPassportOfObject::getId, s -> s));
        for (UpdateDataPassportOfObjectDto dataPassportDto : dataDto) {
            DataPassportOfObject dataPassport = dataPassportDb.get(dataPassportDto.getId());
            dataPassport.setTemplate(builder.getTemplates().get(dataPassportDto.getTemplateId()));
            dataPassport.setPassport(passports.get(dataPassportDto.getObjectId()));
            dataPassportDb.put(dataPassport.getId(), dataPassport);
        }
        return mapper.mapToDataPassportOfObjectDto(repository.saveAll(new ArrayList<>(dataPassportDb.values())));
    }

    private void validateIds(List<Long> ids) {
        Map<Long, DataPassportOfObject> data = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(DataPassportOfObject::getId, m -> m));
        if (data.size() != ids.size() || data.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(data.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("DataPassportOfObject with ids= %s not found", ids));
        }
    }
}