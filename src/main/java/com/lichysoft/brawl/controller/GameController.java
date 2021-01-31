package com.lichysoft.brawl.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lichysoft.brawl.model.Game;
import com.lichysoft.brawl.model.Player;
import com.lichysoft.brawl.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameController {

    private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService=gameService;
    }


    @PostMapping ("/init")
    public ResponseEntity<String> gameInit() {
        return ResponseEntity.ok("Twoja gra została zainicjalizowana \n prosze wybrac pierwszego gracza.");
    }
    @PostMapping ("/players/{playerOneClass}/{playerTwoClass}")
    public ResponseEntity<String> choosePlayers(@PathVariable String playerOneClass, @PathVariable String playerTwoClass) {
        return ResponseEntity.ok(gameService.choosePlayers(playerOneClass,playerTwoClass));

    }
    @PostMapping("/restart")
    public ResponseEntity<String> gameReset() {
        return ResponseEntity.ok(gameService.restartGame());
    }
    @PostMapping("/attack/{playerOneID}/{playerTwoID}")
    public ResponseEntity<String> attackPlayer(@PathVariable Long playerOneID, @PathVariable Long playerTwoID) throws JsonProcessingException {
//        Player returnedPlayer = gameService.attack(playerOneID, playerTwoID);
        String status = gameService.attack2(playerOneID, playerTwoID);

        if (status == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(status);
        }
    }
}
