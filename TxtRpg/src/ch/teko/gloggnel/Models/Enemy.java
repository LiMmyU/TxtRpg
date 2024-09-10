package ch.teko.gloggnel.Models;

public class Enemy implements Character {
    private String name;
    private int health;
    private int attack;
    private int defense;
    private double hitChance;
    private double criticalHitChance;
    private double criticalDamage;

    public Enemy(String name, int level) {
        this.name = name;
        this.health = 50 + level * 10;
        this.attack = 8 + level * 2;
        this.defense = 3 + level * 2;
        this.hitChance = 0.7;
        this.criticalHitChance = 0.15;
        this.criticalDamage = 1.2;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public String getName() {
        return name;
    }

    public double getHitChance() {
        return hitChance;
    }

    public double getCriticalHitChance() {
        return criticalHitChance;
    }

    public double getCriticalDamage() {
        return criticalDamage;
    }
}

