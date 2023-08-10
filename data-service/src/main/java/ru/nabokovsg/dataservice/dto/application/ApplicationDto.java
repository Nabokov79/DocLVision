package ru.nabokovsg.dataservice.dto.application;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.address.AddressDto;
import ru.nabokovsg.dataservice.dto.employee.UltraShortEmployeeDto;
import ru.nabokovsg.dataservice.dto.object.ShortDataObjectsDto;
import ru.nabokovsg.dataservice.dto.workPerformed.WorkPerformedDto;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class ApplicationDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Дата проведения обследования/контроля")
    private LocalDate date;
    @Schema(description = "Адреса места проведения обследования/контроля")
    private AddressDto address;
    @Schema(description = "Индентификатор объекта обследования/контроля")
    private ShortDataObjectsDto object;
    @Schema(description = "Выполненная работа")
    private WorkPerformedDto workPerformed;
    @Schema(description = "Список сотрудников, проводивших обследование/контроль объекта")
    private List<UltraShortEmployeeDto> employees;
    @Schema(description = "Комментарий к заявке")
    private String comment;
}
