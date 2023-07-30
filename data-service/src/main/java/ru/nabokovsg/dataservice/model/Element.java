package ru.nabokovsg.dataservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "elements")
public class Element {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "element")
    private String element;
    @Column(name = "diameter")
    private Integer diameter;
    @Column(name = "thickness")
    private Float thickness;
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "elements_places",
            joinColumns = {@JoinColumn(name = "element_id")},
            inverseJoinColumns = {@JoinColumn(name = "place_id")})
    @ToString.Exclude
    private Set<Place> places;
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "elements_norms",
            joinColumns = {@JoinColumn(name = "element_id")},
            inverseJoinColumns = {@JoinColumn(name = "norm_id")})
    @ToString.Exclude
    private Set<Norm> norms;
}