package ru.nabokovsg.dataservice.dto.requisites;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные реквизитов сотрудника")
public class RequisitesEmployeeDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Номер телефона")
    private String phone;
    @Schema(description = "Факс")
    private String fax;
    @Schema(description = "электронная почта")
    private String email;
}
