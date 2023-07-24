package ru.nabokovsg.dataservice.dto.measuringTool;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class MeasuringToolIds {

    private Long typeId;
    private Long organizationId;
    private Long employeeId;
    private Long manufacturerId;
    private Long toolOwnerId;
}
