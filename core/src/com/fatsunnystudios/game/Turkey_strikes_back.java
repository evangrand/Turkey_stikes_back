package com.fatsunnystudios.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * this is the main class control and manage the whole game, focus on object interactions.<br>
 * update object position and draw them.<br>
 * game rule.
 */
public class Turkey_strikes_back extends ApplicationAdapter {
	SpriteBatch batch;
	EnemyManager musketKit;
	int numberOfEnemy;
	int initialNumberOfEnemy = 2;
	int maxNumberOfEnemy = 5;

	Turkey turkey;

	FeatherControl textureFeatherControl;
	long lastTimeFeather;
	long timeGapFeather = 200000000l;;

	String gameState;
	Texture background1;

	Audios myAudios;

	/////////////myextra////////////
	FontText fontText;
	int score;
	int initialScore = 0;
	////////////////////////////////

	@Override
	public void create () {
		batch = new SpriteBatch();
		musketKit = new EnemyManager();
		numberOfEnemy = initialNumberOfEnemy;
		musketKit.addNEnemyMovement(numberOfEnemy);

		turkey = new Turkey();

		textureFeatherControl = new FeatherControl();
		lastTimeFeather = TimeUtils.nanoTime();

		gameState = "start";

		myAudios = new Audios();

		background1 = new Texture("welcomeScreen.png");

		/////////////myextra////////////
		fontText = new FontText();
		score = initialScore;
		////////////////////////////////
	}

	@Override
	public void dispose() {
		batch.dispose();
		turkey.textureTurkey.dispose();
		textureFeatherControl.disposeFeathers();
		background1.dispose();

		/////////////myextra////////////
		fontText.disposeBitmapFont();
		////////////////////////////////
	}

	@Override
	public void render () {
		updootle();
		draw();
	}

	/**
	 * depends on gameState either assign welcome image, play game, or assign gameover image.<br>
	 * change gamestate.<br>
	 * check finger touch to move player and shot, update position.<br>
	 * check enemy respawn, update position.
	 */
	public void updootle() {
		if(gameState.equalsIgnoreCase("start")){
			background1 = new Texture("welcomeScreen.png");
			if (Gdx.input.justTouched()){
				resetGame();
			}
		}

		else if(gameState.equalsIgnoreCase("play")) {
			musketKit.update();
			Gdx.input.setInputProcessor(new InputProcessor() {
				@Override
				public boolean touchDown(int screenX, int screenY, int pointer, int button) {
					turkey.positionUpdate(screenX, Gdx.graphics.getDeltaTime() * 2);
					// TODO: this sound file not work, use another one
//					myAudios.playSoundTurkeyGobble();
					return true;
				}

				@Override
				public boolean touchUp(int screenX, int screenY, int pointer, int button) {
					if (TimeUtils.nanoTime() - lastTimeFeather > timeGapFeather) {
						lastTimeFeather = TimeUtils.nanoTime();
						textureFeatherControl.addFeather(
								new Vector2(turkey.rectTurkeyBorder.x + turkey.rectTurkeyBorder.width / 2 - Feather.spriteFeatherWidth / 2,
										turkey.rectTurkeyBorder.y + turkey.spriteTurkeyHeight));
						myAudios.playSoundGunshot();
					}
					return true;
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
			if(shouldRespawnEnemy()){
				respawnEnemy();
			}
			textureFeatherControl.update();

		}
		else if(gameState.equalsIgnoreCase("end")){
			background1 = new Texture("gameoverScreen.png");
			if (Gdx.input.justTouched()){
				gameState = "start";
			}
		}
	}

	/**
	 * depends on gameState either display welcome image, play game, or display gameover image.<br>
	 * change gamestate.<br>
	 * draw game object, detect collision, remove object.
	 */
	public void draw() {
		Gdx.gl.glClearColor(1f, 0.5f, 0.3f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if(gameState.equalsIgnoreCase("start")){
			batch.begin();
			batch.draw(background1, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			batch.end();
		}
		else if(gameState.equalsIgnoreCase("play")){
			batch.begin();

			/////////////myextra////////////
			fontText.draw(batch, "Score=" + score, Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 50);
			////////////////////////////////

			musketKit.draw(batch);
			textureFeatherControl.draw(batch);
			turkey.draw(batch);
			for(EnemyMovement enemyMovement : musketKit.arrEnemyManage){
				for(Feather feather : textureFeatherControl.arrTextureFeathers){
					if(feather.rectFeatherBorder.overlaps(enemyMovement.rectEnemy)) {
						myAudios.playSoundGunshot();
						textureFeatherControl.arrTextureFeathers.removeValue(feather, true);
						musketKit.arrEnemyManage.removeValue(enemyMovement, true);

						/////////////myextra////////////
						score++;
						////////////////////////////////
					}
				}
				if(enemyMovement.rectEnemy.overlaps(turkey.rectTurkeyBorder)){
					myAudios.playSoundGunshot();
					musketKit.arrEnemyManage.removeValue(enemyMovement, true);
					resetGame();
					gameState = "end";
				}
			}
			batch.end();
		}
		else if(gameState.equalsIgnoreCase("end")){
			batch.begin();
			batch.draw(background1, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			batch.end();
		}
	}

	/**
	 * check if all enemies destroyed, spawn n+1 more enemies, no more than 5 enemies.
	 */
	public boolean shouldRespawnEnemy(){
		if(musketKit.arrEnemyManage.size == 0){
			if(numberOfEnemy < maxNumberOfEnemy){
				numberOfEnemy++;
			}
			return true;
		}
		return false;
	}

	/**
	 * respawn a new group of enemies
	 */
	public void respawnEnemy(){
		musketKit.arrEnemyManage.clear();
		musketKit.addNEnemyMovement(numberOfEnemy);
	}

	/**
	 * reset all data to start of the game
	 */
	public void resetGame(){
		gameState = "play";
		textureFeatherControl.arrTextureFeathers.clear();
		musketKit.arrEnemyManage.clear();
		numberOfEnemy = initialNumberOfEnemy;
		musketKit.addNEnemyMovement(numberOfEnemy);
		turkey.reset();
		/////////////myextra////////////
		score=initialScore;
		////////////////////////////////
	}
}