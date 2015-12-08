package com.fatsunnystudios.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * this class create and setup Sound and Music for all audio.<br>
 * use this class object to play and dispose audio.<br>
 * after this class object created background music will play forever.
 */
public class Audios {
    Sound soundGunshot;
    Sound soundTurkeyGobble;
    Music musicThanksgivingTheme;

    public Audios(){
        soundGunshot = Gdx.audio.newSound(Gdx.files.internal("gunshot-4.wav"));
        soundTurkeyGobble = Gdx.audio.newSound(Gdx.files.internal("turkey gobble.wav"));

        musicThanksgivingTheme = Gdx.audio.newMusic(Gdx.files.internal("Thanksgiving Theme.mp3"));

        playAndLoopThanksgivingThemeMusic();
    }

    /**
     * call this method in main's dispose to dispose all audio
     */
    public void disposeAllAudios(){
        soundGunshot.dispose();
        soundTurkeyGobble.dispose();
        musicThanksgivingTheme.dispose();
    }

    /**
     * play fire a shot sound
     */
    public void playSoundGunshot(){
        soundGunshot.play();
    }

    /**
     * play turkey gobble sound
     */
    public void playSoundTurkeyGobble(){
        soundTurkeyGobble.play();
    }

    /**
     * thanksgivingTheme music will play forever when this class object got created
     */
    public void playAndLoopThanksgivingThemeMusic(){
//        thanksgivingTheme.setVolume(0.8f);
        musicThanksgivingTheme.setLooping(true);
        musicThanksgivingTheme.play();
    }
}
