package com.lichysoft.brawl.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Player {


    @Id
    @GeneratedValue
    private Long ID;
    private String nickName;
    private int health;
    private int manaPoints;
    private int minAttack;
    private int maxAttack;

    public Player() {}

    public  Player(String name, int hp, int mp, int mina, int maxa) {
        this.nickName = name;
        this.health = hp;
        this.manaPoints = mp;
        this.minAttack = mina;
        this.maxAttack = maxa;
    }
    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getManaPoints() {
        return manaPoints;
    }

    public void setManaPoints(int manaPoints) {
        this.manaPoints = manaPoints;
    }

    public int getMinAttack() {
        return minAttack;
    }

    public void setMinAttack(int minAttack) {
        this.minAttack = minAttack;
    }

    public int getMaxAttack() {
        return maxAttack;
    }

    public void setMaxAttack(int maxAttack) {
        this.maxAttack = maxAttack;
    }

    @Override
    public String toString() {
        return "Player{" +
                "ID=" + ID +
                ", nickName='" + nickName + '\'' +
                ", health=" + health +
                ", manaPoints=" + manaPoints +
                ", minAttack=" + minAttack +
                ", maxAttack=" + maxAttack +
                '}';
    }
}
