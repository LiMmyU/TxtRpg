package ch.teko.gloggnel.Models;

import java.io.Serializable;
import java.util.Random;

public class PlayerModel implements Serializable {

    public double _baseHitChance = 0.5;
    public double _criticalHitChance = 0.05;
    public double _criticalMultiplier = 1.2;
    public int _level = 1;
    public int _experience = 0;
    public int _expThreshhold = 1000;
    public double _armor = 0;
    public double _baseHealth = 100;
    public double _baseMinDamage = 5;
    public double _baseMaxDamage = 10;
    public int _skillPoints = 0;
    public int _gold = 0;

    public PlayerModel(){

    }

}
