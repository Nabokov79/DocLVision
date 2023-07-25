package ru.nabokovsg.dataservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "licenses")
public class License {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_id", referencedColumnName = "id")
    private Organization organization;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "branch_id", referencedColumnName = "id")
    private Branch branch;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;
    @Column(name = "document_type")
    private String documentType;
    @Column(name = "license_number")
    private String licenseNumber;
    @Column(name = "start_date")
    private LocalDate startData;
    @Column(name = "end_date")
    private LocalDate endData;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "issued_license_id", referencedColumnName = "id")
    private Organization issuedLicense;
}