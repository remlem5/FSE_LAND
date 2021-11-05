package com.example.meins.meins1_4.entity;

import javax.persistence.*;

@Entity
public class Department {

    @Id
    @SequenceGenerator(name = "dep_sequence", sequenceName = "dep_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dep_sequence")
    public Long depId;

    public String depName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comp_id", referencedColumnName = "compId")
    public Company company;

}
