package ru.nabokovsg.dataservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.controlType.ControlTypeDto;
import ru.nabokovsg.dataservice.dto.controlType.NewControlTypeDto;
import ru.nabokovsg.dataservice.dto.controlType.UpdateControlTypeDto;
import ru.nabokovsg.dataservice.exceptions.BadRequestException;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mapper.ControlTypeMapper;
import ru.nabokovsg.dataservice.model.ControlType;
import ru.nabokovsg.dataservice.model.Employee;
import ru.nabokovsg.dataservice.repository.ControlTypeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ControlTypeServiceImpl implements ControlTypeService {

    private final ControlTypeRepository repository;
    private final ControlTypeMapper mapper;

    @Override
    public ControlTypeDto save(NewControlTypeDto typeControlDto) {
        if (repository.existsByNameTypeControl(typeControlDto.getNameTypeControl())) {
            throw new BadRequestException(
                    String.format("control type=%s found.", typeControlDto.getNameTypeControl())
            );
        }
        ControlType typeControl = mapper.mapToNewControlType(typeControlDto);
        typeControl.setShortNameTypeControl(typeControl.getShortNameTypeControl().toUpperCase());
        return mapper.mapToControlTypeDto(repository.save(typeControl));
    }

    @Override
    public ControlTypeDto update(UpdateControlTypeDto typeControlDto) {
        if (!repository.existsById(typeControlDto.getId())) {
            throw new NotFoundException(
                    String.format("control type=%s not found for update.", typeControlDto.getNameTypeControl())
            );
        }
        ControlType typeControl = mapper.mapToUpdateControlType(typeControlDto);
        typeControl.setShortNameTypeControl(typeControl.getShortNameTypeControl().toUpperCase());
        return mapper.mapToControlTypeDto(repository.save(typeControl));
    }

    @Override
    public ControlType get(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(String.format("control type with id=%s not found", id)));
    }

    @Override
    public List<ControlTypeDto> getAll() {
        return mapper.mapToControlTypesDto(repository.findAll());
    }

    @Override
    public List<ControlType> getAllByIds(List<Long> ids) {
        List<ControlType> controlTypes = repository.findAllById(ids);
        if (controlTypes.isEmpty()) {
            throw new NotFoundException(String.format("Control type with ids=%s not found", ids));
        }
        return controlTypes;
    }
    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
       throw new NotFoundException(String.format("control type with id=%s not found for delete.", id));
    }
}