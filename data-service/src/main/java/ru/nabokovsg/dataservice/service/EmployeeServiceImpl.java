package ru.nabokovsg.dataservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.employee.EmployeeDto;
import ru.nabokovsg.dataservice.dto.employee.ShortEmployeeDto;
import ru.nabokovsg.dataservice.dto.employee.UpdateEmployeeDto;
import ru.nabokovsg.dataservice.dto.employee.NewEmployeeDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mapper.EmployeeMapper;
import ru.nabokovsg.dataservice.model.Employee;
import ru.nabokovsg.dataservice.model.MeasuringTool;
import ru.nabokovsg.dataservice.repository.CertificateRepository;
import ru.nabokovsg.dataservice.repository.EmployeeRepository;
import ru.nabokovsg.dataservice.repository.MeasuringToolRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;
    private final CertificateRepository certificateRepository;
    private final MeasuringToolRepository measuringToolRepository;
    private final RequisitesService requisitesService;

    @Override
    public ShortEmployeeDto save(NewEmployeeDto employeeDto) {
        Employee employee = repository.save(mapper.mapToEmployee(employeeDto));
        employee.setRequisites(requisitesService.save(employeeDto.getRequisites()));
        return mapper.mapToEmployeeShortDto(employee);
    }

    @Override
    public ShortEmployeeDto update(UpdateEmployeeDto employeeDto) {
        if (!repository.existsById(employeeDto.getId())) {
            throw new NotFoundException(String.format("employee with id=%s not found for update",employeeDto.getId()));
        }
        Employee employee = repository.save(mapper.mapToUpdateEmployee(employeeDto));
        employee.setRequisites(requisitesService.update(employeeDto.getRequisites()));
        return mapper.mapToEmployeeShortDto(employee);
    }

    @Override
    public EmployeeDto get(Long id) {
        return mapper.mapToEmployeeDto(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("employee with id=%s was not found", id))));
    }

    @Override
    public List<ShortEmployeeDto> getAll() {
        return repository.findAll().stream().map(mapper::mapToEmployeeShortDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            updateMeasuringTool(id);
            certificateRepository.deleteAllByEmployeeId(id);
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("employee with id = %s not found for delete",id));
    }

    private void updateMeasuringTool(Long id) {
        Set<MeasuringTool> measuringTools = measuringToolRepository.findAllByEmployeeId(id);
        measuringTools.forEach(measuringTool -> measuringTool.setEmployee(null));
        measuringToolRepository.saveAll(measuringTools);
    }
}
