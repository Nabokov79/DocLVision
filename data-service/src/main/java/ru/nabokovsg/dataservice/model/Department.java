package ru.nabokovsg.dataservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "department")
    private String department;
    @Column(name = "short_name_department")
    private String shortNameDepartment;
    @Column(name = "department_number")
    private Integer departmentNumber;
    @OneToMany(mappedBy = "department",
            orphanRemoval = true,
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY)
    private Set<Building> buildings;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "requisites_id", referencedColumnName = "id")
    private Requisites requisites;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id",  nullable = false)
    private Branch branch;

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", department='" + department + '\'' +
                ", shortNameDepartment='" + shortNameDepartment + '\'' +
                ", departmentNumber=" + departmentNumber +
                ", buildings=" + buildings +
                ", requisites=" + requisites +
                ", branch=" + branch +
                '}';
    }
}