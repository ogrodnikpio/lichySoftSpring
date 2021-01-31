package com.lichysoft.brawl.model;

public class Game {
    private Player playerOne;
    private Player playerTwo;

    public Game() {
    }

    public Game(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }

    @Override
    public String toString() {
        return "[{" +
                "\"playerOne:\"" + playerOne +
                ", \"playerTwo\":" + playerTwo +
                "}]";
    }
}
