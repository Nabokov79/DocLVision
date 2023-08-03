package ru.nabokovsg.dataservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ObjectsIds {

    private Long controlTypeId;
    private Long organizationId;
    private Long employeeId;
    private Long toolOwnerId;
    private Long manufacturerId;
    private Long objectsTypeId;
    private Long buildingId;
    private Long issuedLicenseId;
    private Long branchId;
    private Long departmentId;
    private Long objectId;
}