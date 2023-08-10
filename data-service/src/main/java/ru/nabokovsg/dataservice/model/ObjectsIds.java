package ru.nabokovsg.dataservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class ObjectsIds {

    private Long controlTypeId;
    private Long organizationId;
    private Long employeeId;
    private List<Long> employeesIds;
    private Long toolOwnerId;
    private Long manufacturerId;
    private Long objectsTypeId;
    private Long buildingId;
    private Long issuedLicenseId;
    private Long branchId;
    private Long departmentId;
    private Long objectId;
    private Long templateId;
    private Long reportingDocumentId;
    private Long addressId;
    private Long workPerformedId;
}