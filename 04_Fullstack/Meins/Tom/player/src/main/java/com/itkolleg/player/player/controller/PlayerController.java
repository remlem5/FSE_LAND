package com.itkolleg.player.player.controller;

import com.itkolleg.player.player.entitiy.Player;
import com.itkolleg.player.player.exception.PlayerNotFoundException;
import com.itkolleg.player.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PlayerController {

    //Was ist wenn mehrere Klassen das Interface implementieren?
    @Autowired
    private PlayerService pService;

    @PostMapping("/players")
    public Player savePlayer(@Valid @RequestBody Player player){
        return pService.savePlayer(player);
    }

    @GetMapping("/players/name/{name}")
    public Player fetchPlayerByLastName(@PathVariable("name") String name) throws PlayerNotFoundException {
        return pService.fetchPlayerByName(name);
    }

    @GetMapping("/players/id/{id}")
    public Player fetchPlayerById(@PathVariable("id") long id) throws PlayerNotFoundException {
        return pService.fetchPlayerById(id);
    }

    @PutMapping("/players/{name}/{newName}")
    public String updatePlayerName(@PathVariable("name") String name, @PathVariable("newName") String newName) throws PlayerNotFoundException {
        return pService.updateLastName(name, newName);
    }

    @DeleteMapping("/players/id/{id}")
    public String deletePlayerById(@PathVariable("id") long id){
        return pService.deletePlayerById(id);
    }

    @PostMapping("/players/players")
    public List<Player> savePlayers(@Valid @RequestBody List<Player> player){
        return pService.savePlayers(player);
    }

}
