package com.itkolleg.player.player.entitiy;

import javax.persistence.*;

@Entity
public class Player {

    @Id
    @SequenceGenerator(name = "player_sequence", sequenceName = "player_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "player_sequence")
    public Long id;

    public String firstName;
    public String lastName;
    public int number;
}
