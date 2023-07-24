package ru.nabokovsg.dataservice.dto.department;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.requisites.RequisitesDto;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Краткие данные подразделения филиала организации")
public class ShortDepartmentDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Полное название подразделения филиала организации")
    private String department;
    @Schema(description = "Краткое название подразделения филиала организации")
    private String shortNameDepartment;
    @Schema(description = "Номер подразделения филиала организации")
    private Integer departmentNumber;
    @Schema(description = "Реквизиты")
    private RequisitesDto requisites;
}