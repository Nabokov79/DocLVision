package ru.nabokovsg.dataservice.dto.measuringTool;

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
@Schema(description = "Данные для изменения информации о измерительном инструменте(приборе)")
public class UpdateMeasuringToolDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id measuring tool should not be blank")
    @Positive(message = "id measuring tool must be positive")
    private Long id;
    @Schema(description = "Название")
    @NotBlank(message = "name should not be blank")
    private String name;
    @Schema(description = "Модель")
    @NotBlank(message = "model should not be blank")
    private String model;
    @Schema(description = "Заводской номер")
    @NotNull(message = "work number should not be blank")
    @Positive(message = "work number id must be positive")
    private Integer workNumber;
    @Schema(description = "Назначение")
    @NotBlank(message = "purpose should not be blank")
    private String purpose;
    @Schema(description = "Дата изготовления")
    @NotNull(message = "manufacturing should not be blank")
    private LocalDate manufacturing;
    @Schema(description = "Дата начала эксплуатации")
    @NotNull(message = "exploitation should not be blank")
    private LocalDate exploitation;
    @Schema(description = "Индентификатор организации-изготовителя")
    @NotBlank(message = "manufacturer should not be blank")
    private Long manufacturerId;
    @Schema(description = "Диапазон измерений")
    @NotBlank(message = "measuring range should not be blank")
    private String  measuringRange;
    @Schema(description = "Характеристики")
    @NotBlank(message = "characteristics should not be blank")
    private String characteristics;
    @Schema(description = "Индентификатор владелеца средства(прибора)")
    @NotBlank(message = "owner id should not be blank")
    @Positive(message = "owner id must be positive")
    private Long ownerId;
    @Schema(description = "Дата поверки")
    @NotNull(message = "verification date should not be blank")
    private LocalDate verificationDate;
    @Schema(description = "Дата следующей поверки")
    @NotNull(message = "next verification date should not be blank")
    private LocalDate nextVerificationDate;
    @Schema(description = "Индентификатор метрологической организации")
    @NotNull(message = "organization id should not be blank")
    @Positive(message = "organization id must be positive")
    private Long organizationId;
    @Schema(description = "Номер свидетельства о поверке")
    @NotBlank(message = "certificate number should not be blank")
    private String certificateNumber;
    @Schema(description = "Номер госреестра")
    @NotBlank(message = "registry should not be blank")
    private String registry;
    @Schema(description = "Примечание")
    @NotBlank(message = "note should not be blank")
    private String note;
    @Schema(description = "Индентификатор вида контроля")
    @NotNull(message = "type id should not be blank")
    @Positive(message = "type id must be positive")
    private Long typeId;
    @Schema(description = "Индентификатор сотрудника")
    @Positive(message = "user id must be positive")
    private Long userId;
}
