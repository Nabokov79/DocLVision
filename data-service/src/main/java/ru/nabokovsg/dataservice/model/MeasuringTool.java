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
@Table(name = "measuring_tools")
public class MeasuringTool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "model")
    private String model;
    @Column(name = "work_number")
    private Integer workNumber;
    @Column(name = "purpose")
    private String purpose;
    @Column(name = "manufacturing")
    private LocalDate manufacturing;
    @Column(name = "exploitation")
    private LocalDate exploitation;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manufacturer_id", referencedColumnName = "id")
    private Organization manufacturer;
    @Column(name = "measuring_range")
    private String  measuringRange;
    @Column(name = "characteristics")
    private String characteristics;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tool_owner_id", referencedColumnName = "id")
    private Organization toolOwner;
    @Column(name = "verification_date")
    private LocalDate verificationDate;
    @Column(name = "next_verification_date")
    private LocalDate nextVerificationDate;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_id", referencedColumnName = "id")
    private Organization organization;
    @Column(name = "certificate_number")
    private String certificateNumber;
    @Column(name = "registry")
    private String registry;
    @Column(name = "note")
    private String note;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "control_type_id", referencedColumnName = "id")
    private ControlType controlType;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id",  nullable = false)
    private Employee employee;

}
