package com.itkolleg.player.player.repo;

import com.itkolleg.player.player.entitiy.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepo extends JpaRepository<Team, Long> {

    Team findTeamByTeamId(long id);

    Optional<Team> findTeamByTeamName(String name);
}

