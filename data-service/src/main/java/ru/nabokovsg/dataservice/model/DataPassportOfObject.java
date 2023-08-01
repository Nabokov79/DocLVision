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
@Table(name = "data_passport_of_objects")
public class DataPassportOfObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "template_id", referencedColumnName = "id")
    private ObjectPassportDataTemplate template;
    @Column(name = "meaning")
    private String meaning;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "passport_id",  nullable = false)
    private Passport passport;
}