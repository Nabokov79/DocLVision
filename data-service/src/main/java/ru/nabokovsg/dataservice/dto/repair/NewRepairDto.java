package ru.nabokovsg.dataservice.dto.repair;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Данные нового ремонта")
public class NewRepairDto {

    @Schema(description = "Индентификатор объекта обследования")
    @NotNull(message = "id object data should not be blank")
    @Positive(message = "id object data can only be positive")
    private Long objectId;
    @Schema(description = "Дата ремонта")
    @NotNull(message = "date repair should not be blank")
    private String date;
    @Schema(description = "Описание ремонта")
    @NotBlank(message = "description repair  should not be blank")
    @Min(3)
    private String description;
    @Schema(description = "Индентификатор организации, выполнившей ремонт")
    @NotNull(message = "organization id should not be blank")
    private Long organizationId;
}