package ru.nabokovsg.dataservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "objects_type_data")
public class ObjectsTypeData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "objects_type_id", referencedColumnName = "id")
    private ObjectsType objectsType;
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "objects_type_data_elements",
            joinColumns = {@JoinColumn(name = "objects_type_data_id")},
            inverseJoinColumns = {@JoinColumn(name = "element_id")})
    @ToString.Exclude
    private Set<Element> elements;
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "objects_type_data_defects",
            joinColumns = {@JoinColumn(name = "objects_type_data_id")},
            inverseJoinColumns = {@JoinColumn(name = "defect_id")})
    @ToString.Exclude
    private Set<Defect> defects;
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "objects_type_data_documentations",
            joinColumns = {@JoinColumn(name = "objects_type_data_id")},
            inverseJoinColumns = {@JoinColumn(name = "documentation_id")})
    @ToString.Exclude
    private Set<Documentation> documentations;
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "objects_type_data_object_passport_data_templates",
            joinColumns = {@JoinColumn(name = "objects_type_data_id")},
            inverseJoinColumns = {@JoinColumn(name = "template_id")})
    @ToString.Exclude
    private Set<ObjectPassportDataTemplate> passportDataTemplates;
}