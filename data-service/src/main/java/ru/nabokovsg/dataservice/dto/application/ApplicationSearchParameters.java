package ru.nabokovsg.dataservice.dto.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
public class ApplicationSearchParameters {

    private Long addressId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long objectId;
}