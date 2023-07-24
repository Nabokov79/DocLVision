package ru.nabokovsg.dataservice.dto.organization;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.branch.BranchDto;
import ru.nabokovsg.dataservice.dto.requisites.RequisitesDto;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные о организации")
public class OrganizationDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Полное наименование организации")
    private String organization;
    @Schema(description = "Краткое наименование организации")
    private String shortNameOrganization;
    @Schema(description = "Филиалы организации")
    private List<BranchDto> branches;
    @Schema(description = "Реквизиты")
    private RequisitesDto requisites;
}