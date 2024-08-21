package ch.teko.gloggnel.Models;

import java.io.Serializable;
import java.util.Random;

public class PlayerModel implements Serializable {

    private double _baseHitChance = 0.5;
    private double _criticalHitChance = 0.05;
    private double _criticalMultiplier = 1.2;
    private int _level = 1;
    private int _experience = 0;
    private int _expThreshhold = 0;
    private int _armor = 0;
    private int _baseHealth = 100;
    private int _baseMinDamage = 5;
    private int _baseMaxDamage = 10;
    private int _skillPoints = 0;
    private int _gold = 0;

    public PlayerModel(){


    }

    public double get_baseHitChance() {
        return _baseHitChance;
    }

    public double get_criticalHitChance() { return _criticalHitChance; }

    public double get_criticalMultiplier() { return _criticalMultiplier; }

    public int get_level() { return _level; }

    public int get_experience() { return _experience; }

    public int get_expThreshhold() { return _expThreshhold; }

    public int get_armor() { return _armor; }

    public int get_baseHealth() { return _baseHealth; }

    public int get_skillPoints() { return _skillPoints; }

    public int get_gold() { return _gold; }

    public int get_baseMinDamage(){
        return _baseMinDamage;
    }

    public int get_baseMaxDamage(){
        return _baseMaxDamage;
    }

}
