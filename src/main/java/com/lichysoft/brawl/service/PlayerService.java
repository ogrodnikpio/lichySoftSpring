package com.lichysoft.brawl.service;

import com.lichysoft.brawl.model.Player;
import com.lichysoft.brawl.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private PlayerRepository playerRepository;


    public PlayerService (PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    public Player savePayer(Player player) {
        return playerRepository.save(player);
    }

    public Optional<Player> findByID(Long playerID) {
        return playerRepository.findById(playerID);
    }

}
