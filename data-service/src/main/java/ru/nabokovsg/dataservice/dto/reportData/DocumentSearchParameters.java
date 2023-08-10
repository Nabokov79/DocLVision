package ru.nabokovsg.dataservice.dto.reportData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class DocumentSearchParameters {

    private Long objectId;
    private Long employeeId;
    private Long addressId;
    private Integer documentNumber;
    private String status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}