package ru.nabokovsg.dataservice.dto.repair;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.organization.ShortOrganizationDto;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные ремонта")
public class RepairDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Дата ремонта")
    private String date;
    @Schema(description = "Описание ремонта")
    private String description;
    @Schema(description = "Организация, выполнившая ремонт")
    private ShortOrganizationDto organization;
}