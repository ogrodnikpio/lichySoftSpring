package com.lichysoft.brawl.service;

import com.lichysoft.brawl.model.Player;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameService {
    private PlayerService playerService;

    public GameService(PlayerService playerService) {
        this.playerService = playerService;
    }

    public Player attack(Long attackerID, Long defenderID) {
        Optional<Player> attacker = playerService.findByID(attackerID);
        Optional<Player> defender = playerService.findByID(defenderID);
        if (attacker.isPresent() && defender.isPresent()) {
            defender.get().setHealth(defender.get().getHealth()- attacker.get().getMinAttack());
            playerService.savePayer(defender.get());
            return defender.get();

        }else
            return null;

    }



}
