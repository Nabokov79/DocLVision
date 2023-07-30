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
@Table(name = "norms")
public class Norm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "min_in_percent")
    private Float minInPercent;
    @Column(name = "min")
    private Float min;
    @Column(name = "size_tolerance")
    private Float sizeTolerance;
    @Column(name = "measurement_error")
    private Float measurementError;
}