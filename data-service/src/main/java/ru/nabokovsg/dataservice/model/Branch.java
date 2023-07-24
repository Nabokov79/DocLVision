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
@Table(name = "branches")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "branch")
    private String branch;
    @Column(name = "short_name_branch")
    private String shortNameBranch;
    @OneToMany(mappedBy = "branch",
            orphanRemoval = true,
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY)
    private Set<Department> departments;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "requisites_id", referencedColumnName = "id")
    private Requisites requisites;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id",  nullable = false)
    private Organization organization;

    @Override
    public String toString() {
        return "Branch{" +
                "id=" + id +
                ", branch='" + branch + '\'' +
                ", shortNameBranch='" + shortNameBranch + '\'' +
                ", departments=" + departments +
                ", requisites=" + requisites +
                ", organization=" + organization +
                '}';
    }
}