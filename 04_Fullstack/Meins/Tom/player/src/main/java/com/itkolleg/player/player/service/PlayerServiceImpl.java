package com.itkolleg.player.player.service;

import com.itkolleg.player.player.entitiy.Player;
import com.itkolleg.player.player.exception.PlayerNotFoundException;
import com.itkolleg.player.player.repo.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepo pRepo;

    @Override
    public Player savePlayer(Player player) {
        return pRepo.save(player);
    }


    @Override
    public Player fetchPlayerById(long id) throws PlayerNotFoundException {
        Optional<Player> player = pRepo.findById(id);
        if (!player.isPresent()) {
            throw new PlayerNotFoundException("Player not found");
        }
        return player.get();
    }

    @Override
    public Player fetchPlayerByName(String name) throws PlayerNotFoundException {
        Optional<Player> player = pRepo.findPlayerByLastName(name);
        if (!player.isPresent()) {
            throw new PlayerNotFoundException("Player not found");
        }
        return player.get();
    }

    @Override
    public String updateLastName(String name, String newName) throws PlayerNotFoundException {
        Optional<Player> player = pRepo.findPlayerByLastName(name);
        if (!player.isPresent()) {
            throw new PlayerNotFoundException("Player not found");
        }
        Player tmp = player.get();
        System.out.println(tmp.lastName);
        tmp.lastName = newName;
        pRepo.save(tmp);
        return "Player " + tmp.lastName + " has been updated";
    }

    @Override
    public String deletePlayerById(long id) {
        pRepo.deleteById(id);
        return "Player deleted!";
    }

    @Override
    public List<Player> savePlayers(List<Player> players) {
        pRepo.saveAll(players);
        return players;
    }
}
