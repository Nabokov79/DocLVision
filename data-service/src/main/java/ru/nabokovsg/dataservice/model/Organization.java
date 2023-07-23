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
@Table(name = "organizations")
public class Organization {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "organization")
    private String organization;
    @Column(name = "short_name_organization")
    private String shortNameOrganization;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
    @OneToMany(mappedBy = "organization",
            orphanRemoval = true,
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY)
    private Set<Branch> branches;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "requisites_id", referencedColumnName = "id")
    private Requisites requisites;

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", organization='" + organization + '\'' +
                ", shortNameOrganization='" + shortNameOrganization + '\'' +
                ", address=" + address +
                ", branches=" + branches +
                ", requisites=" + requisites +
                '}';
    }
}