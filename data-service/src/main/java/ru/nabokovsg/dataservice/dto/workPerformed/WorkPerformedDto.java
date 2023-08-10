package ru.nabokovsg.dataservice.dto.workPerformed;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.reportingDocument.ReportingDocumentDto;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные вида работы")
public class WorkPerformedDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Вид работы")
    private String work;
    @Schema(description = "Тип отчетного документа")
    private ReportingDocumentDto reportingDocument;
}