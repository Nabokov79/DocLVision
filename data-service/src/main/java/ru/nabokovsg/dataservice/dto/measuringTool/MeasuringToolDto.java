package ru.nabokovsg.dataservice.dto.measuringTool;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.controlType.ControlTypeDto;
import ru.nabokovsg.dataservice.dto.employee.ShortEmployeeDto;
import ru.nabokovsg.dataservice.dto.organization.UltraShortOrganizationDto;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Измерительный инструмент(прибор)")
public class MeasuringToolDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Название")
    private String toll;
    @Schema(description = "Модель")
    private String model;
    @Schema(description = "Заводской номер")
    private Integer workNumber;
    @Schema(description = "Назначение")
    private String purpose;
    @Schema(description = "Дата изготовления")
    private LocalDate manufacturing;
    @Schema(description = "Дата начала эксплуатации")
    private LocalDate exploitation;
    @Schema(description = "Организация-изготовитель")
    private UltraShortOrganizationDto manufacturer;
    @Schema(description = "Диапазон измерений")
    private String  measuringRange;
    @Schema(description = "Характеристики")
    private String characteristics;
    @Schema(description = "Владелец инструмента")
    private UltraShortOrganizationDto toolOwner;
    @Schema(description = "Дата поверки")
    private LocalDate verificationDate;
    @Schema(description = "Дата следующей поверки")
    private LocalDate nextVerificationDate;
    @Schema(description = "Метрологическая организация")
    private UltraShortOrganizationDto organization;
    @Schema(description = "Номер свидетельства о поверке")
    private String certificateNumber;
    @Schema(description = "Номер госреестра")
    private String registry;
    @Schema(description = "Примечание")
    private String note;
    @Schema(description = "Вид контроля")
    private ControlTypeDto controlType;
    @Schema(description = "Сотрудник")
    private ShortEmployeeDto employee;
}
