package ru.nabokovsg.dataservice.dto.reportData;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.employee.UltraShortEmployeeDto;
import ru.nabokovsg.dataservice.model.Statuses;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Информация о отчетной документации")
public class DocumentInformationDto {

    @Schema(description = "Индентификатор")
    private long id;
    @Schema(description = "Номер документа")
    private Integer documentNumber;
    @Schema(description = "Сотрудник составивший отчет")
    private UltraShortEmployeeDto employee;
    @Schema(description = "Время начала работы над составлением документа")
    private LocalDateTime startDate;
    @Schema(description = "Время окончания работы над составлением документа")
    private LocalDateTime endDate;
    @Schema(description = "Статус документа")
    private Statuses status;
}
