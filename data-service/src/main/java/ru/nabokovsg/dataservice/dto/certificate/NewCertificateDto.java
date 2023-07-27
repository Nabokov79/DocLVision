package ru.nabokovsg.dataservice.dto.certificate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные нового сертификата сотрудника")
public class NewCertificateDto {

    @Schema(description = "Номер сертификата")
    @NotBlank(message = "number should not be blank")
    private String certificateNumber;
    @Schema(description = "Индентификатор вида контроля соглано сертификата")
    @NotNull(message = "type should not be blank")
    @Positive(message = "type id must be positive")
    private Long controlTypeId;
    @Schema(description = "Квалификационный уровень сотрудника по данным из сертификата")
    @NotNull(message = "level should not be blank")
    @Positive(message = "level must be positive")
    private Integer level;
    @Schema(description = "Дата выдачи сертификата специализированной организацией")
    @NotNull(message = "start date should not be blank")
    private LocalDate start;
    @Schema(description = "Дата окончания действия сертификата")
    @NotNull(message = "end date not be blank")
    private LocalDate end;
    @Schema(description = "Шифр объектов, для контроля которых допущен сотрудник согласно данным сертификата")
    @NotBlank(message = "points should not be blank")
    private String points;
    @Schema(description = "Индентификатор организации, выдавшей сертификат")
    @NotNull(message = "organization should not be blank")
    @Positive(message = "organization id must be positive")
    private Long organizationId;
    @Schema(description = "Индентификатор сотрудника, которому принадлежит сертификат")
    @NotNull(message = "employee id should not be blank")
    @Positive(message = "employee id must be positive")
    private Long employeeId;
}