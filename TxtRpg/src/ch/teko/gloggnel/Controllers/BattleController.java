package ch.teko.gloggnel.Controllers;

import ch.teko.gloggnel.Models.EnemyModel;
import ch.teko.gloggnel.Models.PlayerModel;
import ch.teko.gloggnel.Models.StorageModel;

import java.util.Optional;
import java.util.Random;

public class BattleController {

    PlayerModel _player;
    StorageModel _storage;

    public BattleController(PlayerModel player, StorageModel storage){
        _player = player;
        _storage = storage;
    }

    public boolean calculateEnemyHit(){
        boolean enemyIsHit = false;

        if (new Random().nextDouble() < _player._baseHitChance){
            enemyIsHit = true;
        }
        return enemyIsHit;
    }

    public double calculateDamage(){
        double damageDone = Math.floor(new Random().nextDouble(_player._baseMaxDamage - _player._baseMinDamage) + _player._baseMinDamage);;
        if (isCrit()) {
            System.out.println("Critical Hit!");
            damageDone *= (int)Math.floor(_player._criticalMultiplier);
        }
        return damageDone;
    }

    private boolean isCrit()
    {
        return new Random().nextDouble() < _player._criticalHitChance;
    }
}
