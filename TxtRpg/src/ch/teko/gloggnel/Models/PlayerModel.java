package ch.teko.gloggnel.Models;

import java.util.Random;

public class PlayerModel {

    private double _baseHitChance = 0.5;
    private int _baseMinDamage = 5;
    private int _baseMaxDamage = 10;

    public PlayerModel(){

    }

    public double get_baseHitChance() {
        return _baseHitChance;
    }

    public int get_baseMinDamage(){
        return _baseMinDamage;
    }

    public int get_baseMaxDamage(){
        return _baseMaxDamage;
    }
}
