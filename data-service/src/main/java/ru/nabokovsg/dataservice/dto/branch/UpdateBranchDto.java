package ru.nabokovsg.dataservice.dto.branch;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.requisites.UpdateRequisitesDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения информации о подразделении")
public class UpdateBranchDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id should not be blank")
    @Positive(message = "id must be positive")
    private Long id;
    @Schema(description = "Полное название филиала организации")
    @NotBlank(message = "branch should not be blank")
    private String branch;
    @Schema(description = "краткое название филиала организации")
    @NotBlank(message = "short name branch organization should not be blank")
    private String shortNameBranch;
    @Schema(description = "Реквизиты филиала организации")
    private UpdateRequisitesDto requisites;
    @Schema(description = "Индентификатор организации")
    @NotNull(message = "organization id should not be blank")
    @Positive(message = "organization id must be positive")
    private Long organizationId;
}
