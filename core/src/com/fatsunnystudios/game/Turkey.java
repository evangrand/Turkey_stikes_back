package com.fatsunnystudios.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * this class contain all data of a player,<br>
 * this class control and manage player itself,<br>
 * update player position base on finger touch position on the left or right of the player.
 */
public class Turkey {
    static Texture textureTurkey = new Texture(Gdx.files.internal("Turkey.png"));
    Rectangle rectTurkeyBorder;
    int screenWidth = Gdx.graphics.getWidth();
    int screenHeight = Gdx.graphics.getHeight();
    Vector2 vector2Turkeyxy;
    float velocity;
    static float spriteTurkeyWidth = 100;
    static float spriteTurkeyHeight = 100;
    Sprite spriteTurkey;
    //sets the turkey's position/size/etc
    public Turkey(){
        rectTurkeyBorder = new Rectangle();
        rectTurkeyBorder.x = screenWidth / 2 - spriteTurkeyWidth / 2;
        rectTurkeyBorder.y = 50;
        rectTurkeyBorder.width = spriteTurkeyWidth;
        rectTurkeyBorder.height = spriteTurkeyHeight;

        vector2Turkeyxy = new Vector2(0,0);
        velocity = 500f;

        spriteTurkey = new Sprite(textureTurkey);
        spriteTurkey.setPosition(rectTurkeyBorder.x, rectTurkeyBorder.y);
        spriteTurkey.setSize(spriteTurkeyWidth, spriteTurkeyHeight);

    }
    /**
     * movement, relatively intuitive
     */
    public void positionUpdate(int touchX, float deltaTime){
        if (touchX < rectTurkeyBorder.x + spriteTurkeyWidth/2){
            rectTurkeyBorder.x += -velocity * deltaTime;
        }
        else if (touchX > rectTurkeyBorder.x + spriteTurkeyWidth/2){
            rectTurkeyBorder.x += velocity * deltaTime;
        }
        spriteTurkey.setPosition(rectTurkeyBorder.x, rectTurkeyBorder.y);
    }

    /**
     * draw player Sprite
     */
    public void draw(SpriteBatch batch){
        spriteTurkey.draw(batch);
    }

    /**
     * reset player start position
     */
    public void reset(){
        rectTurkeyBorder.x = screenWidth / 2 - spriteTurkeyWidth / 2;
        rectTurkeyBorder.y = 50;
        spriteTurkey.setPosition(rectTurkeyBorder.x, rectTurkeyBorder.y);
    }
}
