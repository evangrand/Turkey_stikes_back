package com.fatsunnystudios.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * this class contain all data of a enemy
 */
public class EnemyMovement {
    static Texture textureEnemy = new Texture(Gdx.files.internal("pilgrimWithMusket.png"));
    Rectangle rectEnemy;
    int screenWidth = Gdx.graphics.getWidth();
    int screenHeight = Gdx.graphics.getHeight();

    Vector2 vector2EnemyXY;
    float velocity;
    static float enemywidth = 100;
    static float enemyheight = 50;

    Sprite enemy;

    /**
     * default constructor create enemy with default setup and speed
     */
    public EnemyMovement() {
        rectEnemy = new Rectangle();
        rectEnemy.x = MathUtils.random(1, screenWidth - enemywidth - 1);
        rectEnemy.y = screenHeight - enemyheight - 1;
        rectEnemy.width = enemywidth;
        rectEnemy.height = enemyheight;

        vector2EnemyXY = new Vector2(0, 0);
        velocity = 200f;
        vector2EnemyXY.x = MathUtils.random(-velocity, velocity) * 2;
        vector2EnemyXY.y = MathUtils.random(-velocity/2,-velocity);
        enemy = new Sprite(textureEnemy);
        enemy.setPosition(rectEnemy.x,rectEnemy.y);
        enemy.setSize(enemywidth,enemyheight);
    }

    /**
     * constructor create enemy with change of something
     */
    public EnemyMovement(float something) {

    }
}
