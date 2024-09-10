package ch.teko.gloggnel.Models;

public interface Character {
    int getHealth();
    void setHealth(int health);
    int getAttack();
    int getDefense();
    double getHitChance();
    double getCriticalHitChance();
    double getCriticalDamage();
    String getName();
}
