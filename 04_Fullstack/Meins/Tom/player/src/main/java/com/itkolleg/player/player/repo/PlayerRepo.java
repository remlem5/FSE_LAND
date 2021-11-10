package com.itkolleg.player.player.repo;

import com.itkolleg.player.player.entitiy.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepo extends JpaRepository<Player, Long> {

    Player findPlayerById(long id);

    //Player findPlayerByLastName(String name);

    Optional<Player> findPlayerByLastName(String name);

}
