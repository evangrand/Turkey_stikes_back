package com.fatsunnystudios.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * this class contain all data of a player bullet,<br>
 * bullet y vector is going up because player shot upward.
 */
public class Feather {
    static Texture textureFeather = new Texture(Gdx.files.internal("turkey feathers.png"));
    Rectangle rectFeatherBorder;
    int screenWidth = Gdx.graphics.getWidth();
    int screenHeight = Gdx.graphics.getHeight();
    Vector2 vector2Featherxy;
    float velocity;

    static float spriteFeatherWidth = 20;
    static float spriteFeatherHeight = 150;
    Sprite spriteFeather;

    public Feather(Vector2 location){
        rectFeatherBorder = new Rectangle();
        rectFeatherBorder.x = location.x;
        rectFeatherBorder.y = location.y;
        rectFeatherBorder.width = spriteFeatherWidth;
        rectFeatherBorder.height = spriteFeatherHeight;

        vector2Featherxy = new Vector2(0,0);
        velocity = 600f;
        vector2Featherxy.x = 0;
        vector2Featherxy.y = velocity;

        spriteFeather = new Sprite(textureFeather);
        spriteFeather.setPosition(rectFeatherBorder.x, rectFeatherBorder.y);
        spriteFeather.setSize(spriteFeatherWidth, spriteFeatherHeight);
    }
}
