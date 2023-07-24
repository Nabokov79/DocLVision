package ru.nabokovsg.dataservice.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.requisites.NewRequisitesDto;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные нового сотрудника")
public class NewEmployeeDto {

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
    private NewRequisitesDto requisites;
}