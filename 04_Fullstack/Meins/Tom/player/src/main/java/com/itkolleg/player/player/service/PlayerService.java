package com.itkolleg.player.player.service;

import com.itkolleg.player.player.entitiy.Player;
import com.itkolleg.player.player.exception.PlayerNotFoundException;

import java.util.List;

public interface PlayerService {

    Player savePlayer(Player player);

    Player fetchPlayerById(long id) throws PlayerNotFoundException;

    Player fetchPlayerByName(String name) throws PlayerNotFoundException;

    String updateLastName(String name, String newName) throws PlayerNotFoundException;

    String deletePlayerById(long id);

    List<Player> savePlayers(List<Player> players);

}
