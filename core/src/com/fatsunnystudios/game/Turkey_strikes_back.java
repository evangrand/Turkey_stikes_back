package com.fatsunnystudios.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Turkey_strikes_back extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	EnemyManager musketKit;

	Turkey turkey;

	Sound ememy;
	Music music;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		musketKit = new EnemyManager();
		musketKit.addNEnemyMovement(2);

		turkey = new Turkey();

		ememy = Gdx.audio.newSound(Gdx.files.internal("gunshot-4.wav"));
		music = Gdx.audio.newMusic(Gdx.files.internal("Thanksgiving Theme.mp3"));
		music.play();
		music.setOnCompletionListener(new Music.OnCompletionListener() {
			@Override
			public void onCompletion(Music music) {
				music.play();
			}
		});
	}

	@Override
	public void render () {
		updootle();
		draw();
	}

	public void updootle() {
		musketKit.update();

		Gdx.input.setInputProcessor(new InputProcessor() {
			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				turkey.positionUpdate(screenX, Gdx.graphics.getDeltaTime() * 2);
				ememy.play();
				return true;
			}
			@Override
			public boolean touchUp(int screenX, int screenY, int pointer, int button) {
				return false;
			}
			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) {
				turkey.positionUpdate(screenX, Gdx.graphics.getDeltaTime());
				return true;
			}
			@Override
			public boolean keyDown(int keycode) {
				return false;
			}
			@Override
			public boolean keyUp(int keycode) {
				return false;
			}
			@Override
			public boolean keyTyped(char character) {
				return false;
			}
			@Override
			public boolean mouseMoved(int screenX, int screenY) {
				return false;
			}
			@Override
			public boolean scrolled(int amount) {
				return false;
			}
		});
	}

	public void draw() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		for(EnemyMovement enemyMovement : musketKit.arrEnemyManage){
			batch.draw(enemyMovement.textureEnemy, enemyMovement.rectEnemy.x, enemyMovement.rectEnemy.y);
		}

		batch.draw(turkey.textureTurkey, turkey.rectTurkeyBorder.x, turkey.rectTurkeyBorder.y);
		batch.end();
	}
}
