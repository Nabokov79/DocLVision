package ru.nabokovsg.dataservice.dto.workPerformed;

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
@Schema(description = "Данные новой вида работы")
public class NewWorkPerformedDto {

    @Schema(description = "Вид работы")
    @NotBlank(message = "work performed should not be blank")
    private String work;
    @Schema(description = "Индентификатор типа отчетного документа")
    @NotNull(message = "reporting document id should not be blank")
    @Positive(message = "reporting document id can only be positive")
    private Long reportingDocumentId;
}