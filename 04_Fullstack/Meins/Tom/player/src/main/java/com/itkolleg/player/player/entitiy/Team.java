package com.itkolleg.player.player.entitiy;

import javax.persistence.*;

@Entity
public class Team {

    @Id
    @SequenceGenerator(name = "team_sequence", sequenceName = "team_sequence", allocationSize = 5)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_sequence")
    public Long teamId;

    public String teamName;
    public String country;
    public int squadAmount;
}
