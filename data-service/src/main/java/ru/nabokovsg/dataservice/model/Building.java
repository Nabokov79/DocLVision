package ru.nabokovsg.dataservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "buildings")
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
    @Column(name = "building_type")
    private String buildingType;
    @Column(name = "login")
    private String login;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id",  nullable = false)
    private Department department;

    @Override
    public String toString() {
        return "Building{" +
                "id=" + id +
                ", address=" + address +
                ", buildingType='" + buildingType + '\'' +
                ", login='" + login + '\'' +
                ", department=" + department +
                '}';
    }
}