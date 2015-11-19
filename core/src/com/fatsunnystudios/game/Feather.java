package com.fatsunnystudios.game;

/**
 * Created by missionbit on 11/18/15.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Feather {
    static Texture textureFeather = new Texture(Gdx.files.internal("feather.png"));
    Rectangle rectFeatherBorder;
    int screenWidth = Gdx.graphics.getWidth();
    int screenHeight = Gdx.graphics.getHeight();
    static int textureWidth = textureFeather.getWidth();
    static int textureHeight = textureFeather.getHeight();
    Vector2 vector2Featherxy;
    float velocity;

    public Feather(Vector2 location){
        rectFeatherBorder = new Rectangle();
        rectFeatherBorder.x = screenWidth / 2 - textureWidth / 2;
        rectFeatherBorder.y = 50;
        rectFeatherBorder.width = textureWidth;
        rectFeatherBorder.height = textureHeight;

        vector2Featherxy = new Vector2(0,0);
        velocity = 600f;
        vector2Featherxy.y =velocity;
    }
}
