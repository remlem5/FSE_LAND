package com.example.meins.meins1_4.entity;

import javax.persistence.*;

@Entity
public class Company {

    @Id
    @SequenceGenerator(name = "comp_sequence", sequenceName = "comp_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comp_sequence")
    public long compId;

    public String compName;

}
