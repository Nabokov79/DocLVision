package ru.nabokovsg.dataservice.dto.documentation;

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
@Schema(description = "Данные для изменения информации о авторе проекта")
public class UpdateDocumentationDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id author should not be blank")
    @Positive(message = "id author must be positive")
    private Long id;
    @Schema(description = "Вид документа")
    private String view;
    @Schema(description = "Номер документа")
    private String number;
    @Schema(description = "Заголовок документа")
    @NotBlank(message = "title should not be blank")
    private String title;
}