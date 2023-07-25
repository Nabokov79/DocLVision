package ru.nabokovsg.dataservice.dto.license;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.branch.UltraShortBranchDto;
import ru.nabokovsg.dataservice.dto.department.UltraShortDepartmentDto;
import ru.nabokovsg.dataservice.dto.organization.UltraShortOrganizationDto;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные лицензии")
public class LicenseDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Организация")
    private UltraShortOrganizationDto organization;
    @Schema(description = "Филиал организации")
    private UltraShortBranchDto branch;
    @Schema(description = "Подразделение филиала организации")
    private UltraShortDepartmentDto department;
    @Schema(description = "Вид документа")
    @NotBlank(message = " document type should not be blank")
    private String documentType;
    @NotBlank(message = "license number should not be blank")
    private String licenseNumber;
    @Schema(description = "Дата выдачи лицензии")
    @NotNull(message = "date license should not be blank")
    private LocalDate startData;
    private LocalDate endData;
    @Schema(description = "Организация, выдавшая лицензию")
    private UltraShortOrganizationDto issuedLicense;
}