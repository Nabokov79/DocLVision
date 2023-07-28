package ru.nabokovsg.dataservice.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.requisites.UpdateRequisitesEmployeeDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения информации о сотруднике")
public class UpdateEmployeeDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id user should not be blank")
    @Positive(message = "id user must be positive")
    private Long id;
    @Schema(description = "Имя")
    @NotBlank(message = "name should not be blank")
    private String name;
    @Schema(description = "Отчество")
    @NotBlank(message = "patronymic should not be blank")
    private String patronymic;
    @Schema(description = "Фамилия")
    @NotBlank(message = "surname should not be blank")
    private String surname;
    @Schema(description = "Должность")
    @NotBlank(message = "post should not be blank")
    private String post;
    @Schema(description = "Реквизиты сотрудника")
    private UpdateRequisitesEmployeeDto requisites;
    @Schema(description = "Индентификатор организации")
    @NotNull(message = "organization id user should not be blank")
    @Positive(message = "organization id user must be positive")
    private Long organizationId;
    @Schema(description = "Индентификатор филиала организации")
    @NotNull(message = "branch id user should not be blank")
    @Positive(message = "branch id user must be positive")
    private Long branchId;
    @Schema(description = "Индентификатор подразделения филиала организации")
    @NotNull(message = "department id user should not be blank")
    @Positive(message = "department id user must be positive")
    private Long departmentId;
}