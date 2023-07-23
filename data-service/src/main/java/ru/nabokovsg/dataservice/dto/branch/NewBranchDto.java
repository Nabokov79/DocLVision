package ru.nabokovsg.dataservice.dto.branch;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.requisites.NewRequisitesDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные нового филиала организации")
public class NewBranchDto {

    @Schema(description = "Полное название филиала организации")
    @NotBlank(message = "branch should not be blank")
    private String branch;
    @Schema(description = "Краткое название филиала организации")
    @NotBlank(message = "short name branch organization should not be blank")
    private String shortNameBranch;
    @Schema(description = "Индентификатор адреса")
    @NotNull(message = "address id should not be blank")
    @Positive(message = "address id must be positive")
    private Long addressId;
    @Schema(description = "Реквизиты филиала организации")
    private NewRequisitesDto requisites;
    @Schema(description = "Индентификатор организации")
    @NotNull(message = "organization id should not be blank")
    @Positive(message = "organization id must be positive")
    private Long organizationId;
}
