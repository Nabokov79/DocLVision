package ru.nabokovsg.dataservice.dto.reportingDocument;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные типа документа")
public class ReportingDocumentDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Тип документа")
    private String document;
}