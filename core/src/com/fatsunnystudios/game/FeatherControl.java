package com.fatsunnystudios.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * this class control and manage player bullets in an Array,<br>
 * use Sprite for image, so it can set size and animation later,<br>
 * update and design player bullets auto movement,<br>
 * draw player bullets to screen.
 */
public class FeatherControl {

    Array<Feather> arrTextureFeathers;

    public FeatherControl(){
        arrTextureFeathers = new Array<Feather>();
    }

    /**
     * this method add 1 player bullet, vector is 90 degree
     */
    public void addFeather(Vector2 location){
        Feather textureFeather = new Feather(location);
        arrTextureFeathers.add(textureFeather);
    }

    /**
     * dispose all player bullets Texture
     */
    public void disposeFeathers(){
        for(Feather textureFeather : arrTextureFeathers){
            textureFeather.textureFeather.dispose();
        }
    }

    /**
     * move player bullet on each update call base on movement design inside this method and Feather setup,<br>
     * remove when reach edge of screen.
     */
    public void update(){
        for(Feather textureFeather : arrTextureFeathers){
            if(textureFeather.rectFeatherBorder.x <= 0){
                arrTextureFeathers.removeValue(textureFeather, true);
            }
            if(textureFeather.rectFeatherBorder.x + textureFeather.spriteFeather.getWidth() >= textureFeather.screenWidth){
                arrTextureFeathers.removeValue(textureFeather, true);
            }
            if(textureFeather.rectFeatherBorder.y + textureFeather.spriteFeather.getHeight() >= textureFeather.screenHeight){
                arrTextureFeathers.removeValue(textureFeather, true);
            }
            textureFeather.rectFeatherBorder.x += textureFeather.vector2Featherxy.x * Gdx.graphics.getDeltaTime();
            textureFeather.rectFeatherBorder.y += textureFeather.vector2Featherxy.y * Gdx.graphics.getDeltaTime();

            textureFeather.spriteFeather.setPosition(textureFeather.rectFeatherBorder.x, textureFeather.rectFeatherBorder.y);
        }
    }

    /**
     * draw each player bullets Sprite
     */
    public void draw(SpriteBatch batch){
        for(Feather textureFeather : arrTextureFeathers){
            textureFeather.spriteFeather.draw(batch);
        }
    }
}
