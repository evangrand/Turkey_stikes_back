package com.fatsunnystudios.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.sun.org.apache.xpath.internal.operations.Variable;


/**
 * Created by missionbit on 11/16/15.
 */
public class EnemyManager {
    Array<EnemyMovement> arrEnemyManage;
    int screenwidth = Gdx.graphics.getWidth();
    int screenheight = Gdx.graphics.getHeight();
    int getScreenheightLevel = 8;
    int getScreenwidthLevel = 10;

    public EnemyManager(){
        arrEnemyManage = new Array<EnemyMovement>();
    }

    public void  addEnemyMovement(){
        EnemyMovement enemyMovement = new EnemyMovement();
        arrEnemyManage.add(enemyMovement);
    }

    public void addNEnemyMovement(int n){
        while(n > 0){
            addEnemyMovement();
            n--;
        }
    }

    public void update(){


        for(EnemyMovement enemyMovement : arrEnemyManage){
            enemyMovement.rectEnemy.x += enemyMovement.vector2EnemyXY.x * Gdx.graphics.getDeltaTime();
            enemyMovement.rectEnemy.y += enemyMovement.vector2EnemyXY.y * Gdx.graphics.getDeltaTime();


        }
    }
}
