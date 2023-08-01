package ru.nabokovsg.dataservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "passports")
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "object_id", referencedColumnName = "id")
    private Objects object;
    @OneToMany(mappedBy = "passport", fetch = FetchType.LAZY)
    private Set<Survey> surveys;
    @OneToMany(mappedBy = "passport", fetch = FetchType.LAZY)
    private Set<Repair> repairs;
    @OneToMany(mappedBy = "passport", fetch = FetchType.LAZY)
    private Set<DataPassportOfObject> dataPassport;
}