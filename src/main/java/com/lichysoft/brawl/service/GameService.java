package com.lichysoft.brawl.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lichysoft.brawl.model.Game;
import com.lichysoft.brawl.model.Player;
import com.lichysoft.brawl.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class GameService {
    private PlayerService playerService;
    private PlayerRepository playerRepository;
    ObjectMapper mapper = new ObjectMapper();
    Long turaGracza;

    public GameService(PlayerService playerService, PlayerRepository playerRepository) {
        this.playerService = playerService;
        this.playerRepository = playerRepository;
    }

    public Player attack(Long attackerID, Long defenderID) {
        Optional<Player> attacker = playerService.findByID(attackerID);
        Optional<Player> defender = playerService.findByID(defenderID);
        if (attacker.isPresent() && defender.isPresent()) {
            defender.get().setHealth(defender.get().getHealth() - playerService.calculatedDamage(attacker.get()));
            playerService.savePayer(defender.get());
            return defender.get();
        } else
            return null;
    }
    public String attack2(Long attackerID, Long defenderID) throws JsonProcessingException {
        Optional<Player> attacker = playerService.findByID(attackerID);
        Optional<Player> defender = playerService.findByID(defenderID);
        String message;
        if (attacker.isPresent() && defender.isPresent()) {
            if (!attacker.get().getID().equals(turaGracza))
                return "Zly gracz probuje atakować. W tej chwili jest tura gracza: " + turaGracza;
            if (attacker.get().getHealth() < 0 || defender.get().getHealth() <0) {
                return "ktorys z graczy jest juz martwy, zacznij gre od nowa";
            }
            int damage = playerService.calculatedDamage(attacker.get());
            defender.get().setHealth(defender.get().getHealth() - damage);
            playerService.savePayer(defender.get());

            Random rand = new Random();
            boolean czyPierwszy = rand.nextBoolean();
            message = "jebłeś za " + damage;
            if (czyPierwszy)
                turaGracza = attacker.get().getID();
            else
                turaGracza = defender.get().getID();
            message += "\nTura gracza o id " + turaGracza;
            if (defender.get().getHealth()>0) {
                message += "\nrozpocznij kolejna ture";
            } else{
                message += "\ngracz wykonujacy atak wygral";
            }
            return message + " \nstatus postaci \n" + mapper.writeValueAsString(playerRepository.findAll());
        } else
            return null;
    }

    public String choosePlayers(String playerOneClass, String playerTwoClass) {
        try {
            Player playerOne = new Player(playerOneClass);
            playerRepository.save(playerOne);
//            game.setPlayerOne(playerOne);
            Player playerTwo = new Player(playerTwoClass);
            playerRepository.save(playerTwo);
//            game.setPlayerTwo(playerTwo);
            Random rand = new Random();
            boolean czyPierwszy = rand.nextBoolean();
            if (czyPierwszy)
                turaGracza = playerOne.getID();
            else
                turaGracza = playerTwo.getID();
            return "Zaczyna gracz o id: " + turaGracza + "\n" + mapper.writeValueAsString(playerRepository.findAll());
        } catch (Exception e) {
            return "Podałeś złą klasę postaci";
        }
    }

    public String restartGame() {
        playerRepository.deleteAll();
//        game.setPlayerOne(null);
//        game.setPlayerTwo(null);
        return "Twoja gra zostala zresetowana wybierz graczy ";

    }


}
