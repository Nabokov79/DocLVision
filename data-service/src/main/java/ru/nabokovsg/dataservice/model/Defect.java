package ru.nabokovsg.dataservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "defects")
public class Defect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "defect")
    private String defect;
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "defects_defect_parameters",
            joinColumns = {@JoinColumn(name = "defect_id")},
            inverseJoinColumns = {@JoinColumn(name = "defect_parameter_id")})
    @ToString.Exclude
    private Set<DefectParameter> defectParameters;
}