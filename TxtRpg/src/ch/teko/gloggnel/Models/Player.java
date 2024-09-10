package ch.teko.gloggnel.Models;

import javax.swing.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

public class Player implements Character, Serializable {
    private static final long serialVersionUID = 1L; // Version ID for serialization

    private String name;
    private int health;
    private int maxHealth;
    private int attack;
    private int defense;
    private int experience;
    private int level;
    private int skillPoints;
    private double hitChance;
    private double criticalHitChance;
    private double criticalDamage;

    public Player(String name) {
        this.name = name;
        this.level = 1;
        this.skillPoints = 0;
        this.experience = 0;
        this.maxHealth = 100;
        this.health = maxHealth;
        this.attack = 10;
        this.defense = 5;
        this.hitChance = 0.5;
        this.criticalHitChance = 0.1;
        this.criticalDamage = 1.5;
    }

    public void levelUp() {

    }

    public void distributeSkillPoints(String attribute, int points) {
        if (skillPoints >= points) {
            switch (attribute.toLowerCase()) {
                case "attack":
                    attack += points;
                    break;
                case "defense":
                    defense += points;
                    break;
                case "health":
                    maxHealth += points * 10;
                    break;
                case "hitchance":
                    hitChance += 0.01;
                    break;
                case "critchance":
                    criticalHitChance += 0.001;
                    break;
                case "critdamage":
                    criticalDamage += 0.001;
                    break;
                default:
                    System.out.println("Invalid attribute.");
                return;
            }
            skillPoints -= points;
            System.out.println("Points distributed: " + points + " to " + attribute);
        } else {
            System.out.println("Not enough skill points.");
        }
    }

    // Getter and Setter methods

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public void addExperience(int exp, JTextArea battleLog) {
        experience += exp;
        if (experience >= level * 100) {
            level++;
            experience = 0;
            maxHealth += 20;
            health = maxHealth;
            System.out.println(name + " is now level " + level + "!");
            int oldSkillPoints = skillPoints;
            String diceResult = rollFiveDice();
            oldSkillPoints = skillPoints - oldSkillPoints;
            battleLog.append("Player rolled " + diceResult + " for " + oldSkillPoints + " Skill Points!\n");
        }
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getSkillPoints() {
        return skillPoints;
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

    //Functions
    public String rollFiveDice() {
        Random random = new Random();
        int[] dice = new int[5];

        for (int i = 0; i < 5; i++) {
            dice[i] = random.nextInt(6) + 1;
        }

        int[] counts = new int[7];
        for (int die : dice) {
            counts[die]++;
        }

        Arrays.sort(counts);

        if (counts[6] == 5) {
            this.skillPoints += 30;
            return "Five of a Kind";
        } else if (counts[6] == 4) {
            this.skillPoints += 23;
            return "Four of a Kind";
        } else if (counts[6] == 3 && counts[5] == 2) {
            this.skillPoints += 20;
            return "Full House";
        } else if (counts[6] == 3) {
            this.skillPoints += 15;
            return "Three of a Kind";
        } else if (counts[6] == 2 && counts[5] == 2) {
            this.skillPoints += 8;
            return "Two Pairs";
        } else if (counts[6] == 2) {
            this.skillPoints += 5;
            return "A Pair";
        } else if (isStraight(dice)) {
            this.skillPoints += 25;
            return "Straight";
        } else {
            this.skillPoints += 1;
            return "Nothing";
        }
    }

    private static boolean isStraight(int[] dice) {
        Arrays.sort(dice);
        return (dice[0] == 1 && dice[1] == 2 && dice[2] == 3 && dice[3] == 4 && dice[4] == 5) ||
                (dice[0] == 2 && dice[1] == 3 && dice[2] == 4 && dice[3] == 5 && dice[4] == 6);
    }
}

