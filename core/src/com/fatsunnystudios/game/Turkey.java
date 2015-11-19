package com.fatsunnystudios.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by missionbit on 11/4/15.
 */



public class Turkey {
    static Texture textureTurkey = new Texture(Gdx.files.internal("Turkey.png"));
    Rectangle rectTurkeyBorder;
    int screenWidth = Gdx.graphics.getWidth();
    int screenHeight = Gdx.graphics.getHeight();
    static int textureWidth = textureTurkey.getWidth();
    static int textureHeight = textureTurkey.getHeight();
    Vector2 vector2Turkeyxy;
    float velocity;
//sets the turkey's position/size/etc
    public Turkey(){
        rectTurkeyBorder = new Rectangle();
        rectTurkeyBorder.x = screenWidth / 2 - textureWidth / 2;
        rectTurkeyBorder.y = 50;
        rectTurkeyBorder.width = textureWidth;
        rectTurkeyBorder.height = textureHeight;

        vector2Turkeyxy = new Vector2(0,0);
        velocity = 500f;

    }
//movement, relatively intuitive
    public void positionUpdate(int touchX, float deltaTime){
        if (touchX < rectTurkeyBorder.x + textureWidth/2){
            rectTurkeyBorder.x += -velocity * deltaTime;
        }
        else if (touchX > rectTurkeyBorder.x + textureWidth/2){
            rectTurkeyBorder.x += velocity * deltaTime;
        }
    }

    public void reset(){
        rectTurkeyBorder.x = screenWidth / 2 - textureWidth / 2;
        rectTurkeyBorder.y = 1;
    }
}
