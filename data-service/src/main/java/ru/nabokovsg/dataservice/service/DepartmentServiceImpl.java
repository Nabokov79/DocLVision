package ru.nabokovsg.dataservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.department.DepartmentDto;
import ru.nabokovsg.dataservice.dto.department.NewDepartmentDto;
import ru.nabokovsg.dataservice.dto.department.ShortDepartmentDto;
import ru.nabokovsg.dataservice.dto.department.UpdateDepartmentDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mapper.BranchMapper;
import ru.nabokovsg.dataservice.mapper.DepartmentMapper;
import ru.nabokovsg.dataservice.model.Department;
import ru.nabokovsg.dataservice.repository.DepartmentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository repository;
    private final DepartmentMapper mapper;
    private final RequisitesService requisitesService;
    private final BranchService branchService;
    private final BranchMapper branchMapper;

    @Override
    public DepartmentDto save(NewDepartmentDto departmentDto) {
        Department department = mapper.mapToNewDepartment(departmentDto);
        department.setRequisites(requisitesService.save(departmentDto.getRequisites()));
        department.setBranch(branchMapper.mapToBranch(branchService.get(departmentDto.getBranchId())));
        return mapper.mapToDepartmentDto(repository.save(department));
    }

    @Override
    public DepartmentDto update(UpdateDepartmentDto departmentDto) {
        Department department = mapper.mapToUpdateDepartment(departmentDto);
        department.setRequisites(requisitesService.update(departmentDto.getRequisites()));
        department.setBranch(branchMapper.mapToBranch(branchService.get(departmentDto.getBranchId())));
        return mapper.mapToDepartmentDto(repository.save(department));
    }

    @Override
    public DepartmentDto get(Long id) {
        return mapper.mapToDepartmentDto(
                repository.findById(id).orElseThrow(
                        () -> new NotFoundException(String.format("Department with id=%s not found", id)))
        );
    }

    @Override
    public List<ShortDepartmentDto> getAll(Long branchId) {
        return mapper.mapToShortDepartmentDto(
                repository.findByBranch(branchMapper.mapToBranch(branchService.get(branchId)))
        );
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Department with id=%s not found for delete.", id));
    }
}