package ru.nabokovsg.dataservice.dto.place;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Наименование нового места замера")
public class NewPlaceDto {

    @Schema(description = "Место замера на элементе")
    @NotBlank(message = "element place should not be blank")
    private String place;
}
