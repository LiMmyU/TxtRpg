package ch.teko.gloggnel.Models;

public class EnemyModel {

    private PlayerModel _player;

    private double _baseHitChance;
    private double _armor;
    private double _baseHealth;
    private double _baseMinDamage;
    private double _baseMaxDamage;

    public EnemyModel(PlayerModel player){
        _player = player;
        this._baseHitChance = Math.floor(_player._baseHitChance * 0.8);
        this._armor = Math.floor(_player._armor * 0.8);
        this._baseHealth = Math.floor(_player._baseHealth * 0.95);
        this._baseMinDamage = Math.floor(_player._baseMinDamage * 0.75);
        this._baseMaxDamage = Math.floor(_player._baseMaxDamage * 0.75);
    }


}
