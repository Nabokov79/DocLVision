package ru.nabokovsg.dataservice.dto.place;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения наименования места замера")
public class UpdatePlaceDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "place id should not be blank")
    @Positive(message = "place id can only be positive")
    private Long id;
    @Schema(description = "Наименование места замера на элементе")
    @NotBlank(message = "element place should not be blank")
    private String place;
}
