package ru.nabokovsg.dataservice.dto.dataPassportOfObject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Новые данные паспорта")
public class NewDataPassportOfObjectDto {

    @Schema(description = "Индентификатор объекта обследования")
    @NotNull(message = "id object data should not be blank")
    @Positive(message = "id object data can only be positive")
    private Long objectId;
    @Schema(description = "Индентификатор шаблона данных паспорта объекта")
    @NotNull(message = "id template should not be blank")
    @Positive(message = "id template can only be positive")
    private Long templateId;
    @Schema(description = "Значение данных паспорта объекта")
    @NotBlank(message = "meaning should not be blank")
    private String meaning;
}