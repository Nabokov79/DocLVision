package ru.nabokovsg.dataservice.model;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "objects")
public class Objects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "objects_type_id", referencedColumnName = "id")
    private ObjectsType objectsType;
    @Column(name = "model")
    private String model;
    @Column(name = "number")
    private Integer number;
    @Column(name = "volume")
    private Integer volume;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "building_id", referencedColumnName = "id")
    private Building building;
}