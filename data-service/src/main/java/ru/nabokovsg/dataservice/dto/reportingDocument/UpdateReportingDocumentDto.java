package ru.nabokovsg.dataservice.dto.reportingDocument;

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
@Schema(description = "Данные для изменения информации о типе документа")
public class UpdateReportingDocumentDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id reporting document should not be blank")
    @Positive(message = "id reporting document must be positive")
    private Long id;
    @Schema(description = "Тип документа")
    @NotBlank(message = "reporting document should not be blank")
    private String document;
}