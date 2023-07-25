package ru.nabokovsg.dataservice.dto.branch;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Основные сведения о филиале организации")
public class UltraShortBranchDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Полное название филиала организации")
    private String branch;
    @Schema(description = "краткое название филиала организации")
    private String shortNameBranch;
}