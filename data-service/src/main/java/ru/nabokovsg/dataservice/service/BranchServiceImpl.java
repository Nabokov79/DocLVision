package ru.nabokovsg.dataservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.branch.BranchDto;
import ru.nabokovsg.dataservice.dto.branch.NewBranchDto;
import ru.nabokovsg.dataservice.dto.branch.ShortBranchDto;
import ru.nabokovsg.dataservice.dto.branch.UpdateBranchDto;
import ru.nabokovsg.dataservice.exceptions.BadRequestException;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mapper.BranchMapper;
import ru.nabokovsg.dataservice.mapper.OrganizationMapper;
import ru.nabokovsg.dataservice.model.Branch;
import ru.nabokovsg.dataservice.repository.BranchRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository repository;
    private final BranchMapper mapper;
    private final OrganizationService organizationService;
    private final OrganizationMapper organizationMapper;
    private final RequisitesService requisitesService;

    @Override
    public BranchDto save(NewBranchDto branchDto) {
        Branch branch = mapper.mapToNewBranch(branchDto);
        branch.setRequisites(requisitesService.save(branchDto.getRequisites()));
        branch.setOrganization(
                organizationMapper.mapToOrganization(organizationService.get(branchDto.getOrganizationId())));
        return mapper.mapToBranchDto(repository.save(branch));
    }

    @Override
    public BranchDto update(UpdateBranchDto branchDto) {
        if (repository.existsById(branchDto.getId())) {
            Branch branch = mapper.mapToUpdateBranch(branchDto);
            branch.setRequisites(requisitesService.update(branchDto.getRequisites()));
            branch.setOrganization(
                    organizationMapper.mapToOrganization(organizationService.get(branchDto.getOrganizationId())));
            return mapper.mapToBranchDto(repository.save(branch));
        }
        throw new BadRequestException(String.format("Branch wth id=%s not found for update", branchDto.getId()));
    }

    @Override
    public BranchDto get(Long id) {
        return mapper.mapToBranchDto(repository.findById(id)
                              .orElseThrow(() -> new NotFoundException(String.format("Branch wth id=%s not found", id)))
        );
    }

    @Override
    public List<ShortBranchDto> getAll(Long organizationId) {
        return mapper.mapToShortBranchDto(
                repository.findAllByOrganization(
                        organizationMapper.mapToOrganization(organizationService.get(organizationId))
                )
        );
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Branch wth id=%s not found for delete", id));
    }
}