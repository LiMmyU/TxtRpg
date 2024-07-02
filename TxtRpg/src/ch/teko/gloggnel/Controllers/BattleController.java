package ch.teko.gloggnel.Controllers;

import ch.teko.gloggnel.Models.PlayerModel;

import java.util.Random;

public class BattleController {

    PlayerModel _player = new PlayerModel();

    public BattleController(){

    }

    public boolean calculateEnemyHit(){
        boolean enemyIsHit = false;

        if (new Random().nextDouble() < _player.get_baseHitChance()){
            enemyIsHit = true;
        }
        return enemyIsHit;
    }

    public int calculateDamage(){
        return new Random().nextInt(_player.get_baseMaxDamage()- _player.get_baseMinDamage()) + _player.get_baseMinDamage();
    }
}
