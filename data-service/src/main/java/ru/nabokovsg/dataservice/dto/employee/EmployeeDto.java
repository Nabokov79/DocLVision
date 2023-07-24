package ru.nabokovsg.dataservice.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.certificate.CertificateDto;
import ru.nabokovsg.dataservice.dto.measuringTool.MeasuringToolDto;
import ru.nabokovsg.dataservice.dto.requisites.RequisitesDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные о сотруднике")
public class EmployeeDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Имя")
    private String name;
    @Schema(description = "Отчество")
    private String patronymic;
    @Schema(description = "Фамилия")
    private String surname;
    @Schema(description = "Должность")
    private String post;
    @Schema(description = "Реквизиты филиала организации")
    private RequisitesDto requisites;
    @Schema(description = "Список сертификатов сотрудника")
    private List<CertificateDto> certificate;
    @Schema(description = "Список средств(приборов) закрепленных за сотрудником")
    private List<MeasuringToolDto> measuringTool;
}