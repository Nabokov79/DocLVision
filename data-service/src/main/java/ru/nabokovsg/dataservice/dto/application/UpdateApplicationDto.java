package ru.nabokovsg.dataservice.dto.application;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения информации о заявки")
public class UpdateApplicationDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id tank passport should not be blank")
    @Positive(message = "id tank passport must be positive")
    private Long id;
    @Schema(description = "Дата проведения обследования/контроля")
    @NotNull(message = "date should not be blank")
    private LocalDate date;
    @Schema(description = "Индентификатор адреса места проведения обследования/контроля")
    @NotNull(message = "address id should not be blank")
    @Positive(message = "address id can only be positive")
    private Long addressId;
    @Schema(description = "Индентификатор объекта обследования/контроля")
    @NotNull(message = "object id should not be blank")
    @Positive(message = "object id can only be positive")
    private Long objectId;
    @Schema(description = "Индентификатор выполненной работы")
    @NotNull(message = "work performed id should not be blank")
    @Positive(message = "work performed id can only be positive")
    private Long workPerformedId;
    @Schema(description = "Список индентификаторов сотрудников, проводивших обследование/контроль объекта")
    private List<Long> employeesIds;
    @Schema(description = "Комментарий к заявке")
    private String comment;
}
