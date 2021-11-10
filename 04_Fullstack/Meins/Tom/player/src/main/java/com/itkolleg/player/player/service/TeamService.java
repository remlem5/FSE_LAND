package com.itkolleg.player.player.service;

import com.itkolleg.player.player.entitiy.Team;
import com.itkolleg.player.player.exception.TeamNotFoundException;

public interface TeamService {

    Team saveTeam (Team team);

    Team fetchTeamById(long id) throws TeamNotFoundException;

    Team  fetchTeamByTeamName (String teamName) throws TeamNotFoundException;

    Team deleteTeamById(long id);
}
