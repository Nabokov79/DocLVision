package ru.nabokovsg.dataservice.dto.branch;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.address.AddressDto;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Краткие сведения о филиале организации")
public class ShortBranchDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Полное название филиала организации")
    private String branch;
    @Schema(description = "краткое название филиала организации")
    private String shortNameBranch;
    @Schema(description = "Адрес филиала организации")
    private AddressDto address;
}
