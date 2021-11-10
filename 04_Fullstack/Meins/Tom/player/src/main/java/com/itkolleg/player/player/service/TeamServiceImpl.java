package com.itkolleg.player.player.service;

import com.itkolleg.player.player.entitiy.Player;
import com.itkolleg.player.player.entitiy.Team;
import com.itkolleg.player.player.exception.PlayerNotFoundException;
import com.itkolleg.player.player.exception.TeamNotFoundException;
import com.itkolleg.player.player.repo.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepo tRepo;

    @Override
    public Team saveTeam(Team team) {
        return tRepo.save(team);
    }

    @Override
    public Team fetchTeamById(long id) throws TeamNotFoundException {
        Optional<Team> team = tRepo.findById(id);
        if (!team.isPresent()) {
            throw new TeamNotFoundException("Player not found");
        }
        return team.get();
    }

    @Override
    public Team fetchTeamByTeamName(String teamName) throws TeamNotFoundException {
        Optional<Team> team = tRepo.findTeamByTeamName(teamName);
        if (!team.isPresent()) {
            throw new TeamNotFoundException("Player not found");
        }
        return team.get();
    }

    @Override
    public Team deleteTeamById(long id) {
        return null;
    }
}
