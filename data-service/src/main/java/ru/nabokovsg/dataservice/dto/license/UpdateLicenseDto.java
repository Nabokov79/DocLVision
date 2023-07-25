package ru.nabokovsg.dataservice.dto.license;

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
@Schema(description = "Данные для изменения иформации о лицензии")
public class UpdateLicenseDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "license id should not be blank")
    @Positive(message = "license id can only be positive")
    private Long id;
    @Schema(description = "Индентификатор организации")
    private Long organizationId;
    @Schema(description = "Индентификатор филиала организации")
    private Long branchId;
    @Schema(description = "Индентификатор подразделения филиала организации")
    private Long departmentId;
    @Schema(description = "Вид документа")
    @NotBlank(message = " document type should not be blank")
    private String documentType;
    @NotBlank(message = "license number should not be blank")
    private String licenseNumber;
    @Schema(description = "Дата выдачи лицензии")
    @NotNull(message = "date license should not be blank")
    private LocalDate startData;
    private LocalDate endData;
    @Schema(description = "Индентификатор организации, выдавшей лицензию")
    @NotNull(message = "issuedLicense id should not be blank")
    @Positive(message = "issuedLicense id can only be positive")
    private Long issuedLicenseId;
}