package ru.nabokovsg.dataservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "patronymic", nullable = false)
    private String patronymic;
    @Column(name = "surname", nullable = false)
    private String surname;
    @Column(name = "post", nullable = false)
    private String post;
    @OneToOne
    @JoinColumn(name = "requisites_id", referencedColumnName = "id")
    private Requisites requisites;
    @OneToOne
    @JoinColumn(name = "organization_id", referencedColumnName = "id")
    private Organization organization;
    @OneToOne
    @JoinColumn(name = "branch_id", referencedColumnName = "id")
    private Branch branch;
    @OneToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;
    @OneToMany(mappedBy = "employee",
            orphanRemoval = true,
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Certificate> certificate = new HashSet<>();
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<MeasuringTool> measuringTool = new HashSet<>();
}