package com.itkolleg.player.player.controller;

import com.itkolleg.player.player.entitiy.Player;
import com.itkolleg.player.player.entitiy.Team;
import com.itkolleg.player.player.exception.PlayerNotFoundException;
import com.itkolleg.player.player.service.PlayerService;
import com.itkolleg.player.player.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TeamController {

    //Was ist wenn mehrere Klassen das Interface implementieren?
    @Autowired
    private TeamService tService;

    @PostMapping("/team")
    public Team saveTeam(@Valid @RequestBody Team team){
        return tService.saveTeam(team);
    }

}
