package ru.nabokovsg.dataservice.dto.passport;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.object.ShortDataObjects;
import ru.nabokovsg.dataservice.dto.repair.RepairDto;
import ru.nabokovsg.dataservice.dto.survey.SurveyDto;
import ru.nabokovsg.dataservice.model.DataPassportOfObject;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Паспортные данные объекта обследования")
public class PassportDto {

    @Schema(description = "Индентификатор")
    private long id;
    @Schema(description = "Объект обследования")
    private ShortDataObjects object;
    @Schema(description = "Список проведенных обследований")
    private List<SurveyDto> surveys;
    @Schema(description = "Список произведенных ремонтов")
    private List<RepairDto> repairs;
    @Schema(description = "Паспортные данные объекта обследования")
    private Set<DataPassportOfObject> dataPassport;
}