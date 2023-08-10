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
@Table(name = "works_performed")
public class WorkPerformed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "work")
    private String work;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reporting_document_id", referencedColumnName = "id")
    private ReportingDocument reportingDocument;
}