package com.fatsunnystudios.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

/**
 * this class control and manage enemies in an Array,<br>
 * add multiple enemies at once, might add code to change something about the enemy<br>
 * use Sprite for image, so it can set size and animation later,<br>
 * update and design enemy auto movement,<br>
 * draw enemy to screen.
 */
public class EnemyManager {
    Array<EnemyMovement> arrEnemyManage;


    public EnemyManager() {
        arrEnemyManage = new Array<EnemyMovement>();
    }

    public void addEnemyMovement() {
        EnemyMovement enemyMovement = new EnemyMovement();
        arrEnemyManage.add(enemyMovement);
    }

    /**
     * add n default enemies
     */
    public void addNEnemyMovement(int n) {
        while (n > 0) {
            addEnemyMovement();
            n--;
        }
    }

    /**
     * move enemy on each update call base on movement design inside this method and EnemyMovement setup,<br>
     * bounce all 4 sides.
     */
    public void update() {
        for (EnemyMovement enemyMovement : arrEnemyManage) {
            if (enemyMovement.rectEnemy.x <= 0) {
                enemyMovement.vector2EnemyXY.x=-enemyMovement.vector2EnemyXY.x;
            }
            if (enemyMovement.rectEnemy.y <= 0) {
                enemyMovement.vector2EnemyXY.y=-enemyMovement.vector2EnemyXY.y;
            }
            if (enemyMovement.rectEnemy.x + enemyMovement.enemy.getWidth() >= enemyMovement.screenWidth) {
                enemyMovement.vector2EnemyXY.x=-enemyMovement.vector2EnemyXY.x;
            }
            if (enemyMovement.rectEnemy.y + enemyMovement.enemy.getHeight() >= enemyMovement.screenHeight) {
                enemyMovement.vector2EnemyXY.y=-enemyMovement.vector2EnemyXY.y;
            }
            enemyMovement.rectEnemy.x+=enemyMovement.vector2EnemyXY.x*Gdx.graphics.getDeltaTime();
            enemyMovement.rectEnemy.y+=enemyMovement.vector2EnemyXY.y*Gdx.graphics.getDeltaTime();
            enemyMovement.enemy.setPosition(enemyMovement.rectEnemy.x,enemyMovement.rectEnemy.y);
        }
    }

    /**
     * draw each enemies Sprite
     */
    public void draw(SpriteBatch batch){
        for(EnemyMovement enemyMovement:arrEnemyManage){
            enemyMovement.enemy.draw(batch);
        }
    }
}
