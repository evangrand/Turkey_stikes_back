package com.fatsunnystudios.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.security.PublicKey;

/**
 * Created by missionbit on 11/4/15.
 */
public class EnemyMovement {
    static Texture textureEnemy = new Texture(Gdx.files.internal("pilgrimWithMusket.png"));
    Rectangle rectEnemy;
    int screenWidth = Gdx.graphics.getWidth();
    int screenHeight = Gdx.graphics.getHeight();
    static int texturewidth = textureEnemy.getWidth();
    static int texturehight = textureEnemy.getHeight();
    Vector2 vector2EnemyXY;
    int velocity;

    public EnemyMovement() {
        rectEnemy = new Rectangle();
        rectEnemy.x = MathUtils.random(1, screenWidth - texturewidth - 1);
        rectEnemy.y = screenHeight - texturehight - 1;
        rectEnemy.width = texturewidth;
        rectEnemy.height = texturehight;

        vector2EnemyXY = new Vector2(0, 0);
        velocity = 200;
        vector2EnemyXY.x = MathUtils.random(-velocity, velocity) * 2;
        vector2EnemyXY.y = -velocity;

    }

    public EnemyMovement(float something) {

    }
}
