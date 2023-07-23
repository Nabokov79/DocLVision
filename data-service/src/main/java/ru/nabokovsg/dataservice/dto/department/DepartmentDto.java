package ru.nabokovsg.dataservice.dto.department;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.address.AddressDto;
import ru.nabokovsg.dataservice.dto.branch.BranchDto;
import ru.nabokovsg.dataservice.dto.requisites.RequisitesDto;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные подразделения филиала организации")
public class DepartmentDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Полное название подразделения филиала организации")
    private String department;
    @Schema(description = "Краткое название подразделения филиала организации")
    private String shortNameDepartment;
    @Schema(description = "Номер подразделения филиала организации")
    private Integer departmentNumber;
    @Schema(description = "Адрес подразделения филиала организации")
    private AddressDto address;
    @Schema(description = "Реквизиты подразделения филиала организации")
    private RequisitesDto requisites;
    @Schema(description = "Филиал организации")
    @NotNull(message = "branch id should not be blank")
    @Positive(message = "branch id must be positive")
    private BranchDto branch;
}