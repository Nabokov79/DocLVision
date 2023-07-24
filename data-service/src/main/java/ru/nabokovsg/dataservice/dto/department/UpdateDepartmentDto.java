package ru.nabokovsg.dataservice.dto.department;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.requisites.UpdateRequisitesDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения информации о подразделении филиала организации")
public class UpdateDepartmentDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id should not be blank")
    @Positive(message = "id must be positive")
    private Long id;
    @Schema(description = "Полное название подразделения филиала организации")
    @NotBlank(message = "branch should not be blank")
    private String department;
    @Schema(description = "Краткое название подразделения филиала организации")
    private String shortNameDepartment;
    @Schema(description = "Номер подразделения филиала организации")
    private Integer departmentNumber;
    @Schema(description = "Реквизиты подразделения филиала организации")
    private UpdateRequisitesDto requisites;
    @Schema(description = "Индентификатор филиала организации")
    @NotNull(message = "branch id should not be blank")
    @Positive(message = "branch id must be positive")
    private Long branchId;
}