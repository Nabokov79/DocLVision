package ru.nabokovsg.dataservice.dto.certificate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.controlType.ControlTypeDto;
import ru.nabokovsg.dataservice.dto.organization.UltraShortOrganizationDto;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Возвращаемый сертификат аттестации сотрудника.")
public class CertificateDto {

    @Schema(description = "Индентификатор")
    private long id;
    @Schema(description = "Номер сертификата")
    private String certificateNumber;
    @Schema(description = "Вид контроля соглано сертификата")
    private ControlTypeDto controlType;
    @Schema(description = "Квалификационный уровень сотрудника по данным из сертификата")
    private Integer level;
    @Schema(description = "Дата выдачи сертификата специализированной организацией")
    private LocalDate startDate;
    @Schema(description = "Дата окончания действия сертификата")
    private LocalDate endDate;
    @Schema(description = "Шифр объектов, для контроля которых допущен сотрудник согласно данным сертификата")
    private String points;
    @Schema(description = "Организация, выдавшая сертификат")
    private UltraShortOrganizationDto organization;
}