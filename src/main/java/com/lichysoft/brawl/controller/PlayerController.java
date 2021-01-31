package com.lichysoft.brawl.controller;

import com.lichysoft.brawl.model.Player;
import com.lichysoft.brawl.service.GameService;
import com.lichysoft.brawl.service.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    private PlayerService playerService;
    private GameService gameService;

    public PlayerController(PlayerService playerService, GameService gameService) {
        this.playerService = playerService;
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<List<Player>> returnAllPlayers() {
        return ResponseEntity.ok(playerService.findAll());
    }

    @PostMapping
    public ResponseEntity<Player> savePlayer(@RequestBody Player player) {

        return ResponseEntity.ok(playerService.savePayer(player));

    }

    @PostMapping("/delete/{playerID}")
    public String deletePlayer(@PathVariable Long playerID) {
        playerService.deletePlayerByID(playerID);
        return "usuniety";
    }

}
