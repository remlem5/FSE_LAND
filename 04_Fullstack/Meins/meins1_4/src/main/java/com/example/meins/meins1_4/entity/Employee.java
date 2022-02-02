package com.example.meins.meins1_4.entity;

import javax.persistence.*;

@Entity
public class Employee {

    @Id
    @SequenceGenerator(name = "emp_sequence", sequenceName = "emp_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_sequence")
    public Long empId;

    public String firstName;
    public String lastName;

    /*@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comp_id", referencedColumnName = "compId")
    public Company company;*/

    @ManyToOne()
    @JoinColumn(name = "dep_id", referencedColumnName = "depId")
    public Department department;

}
