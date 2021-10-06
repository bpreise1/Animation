package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {

	SpriteBatch batch;
	private TextureAtlas basketballAtlas;
	private Animation<TextureRegion> animation;
	private float elapsedTime = 0f;
	private int x, y, moveToX;
	private LastTouch lastTouch;
	int dx = 10;

	
	@Override
	public void create () {
		batch = new SpriteBatch();

		basketballAtlas = new TextureAtlas(Gdx.files.internal("Basketball.atlas"));
		animation = new Animation<TextureRegion>(.075f, basketballAtlas.getRegions());
		x = 50;
		y = 50;

		Gdx.input.setInputProcessor(new InputAdapter() {
			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				moveToX = screenX;
				lastTouch = new LastTouch(screenX, screenY);
				return true;
			}
		});
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);

		elapsedTime += Gdx.graphics.getDeltaTime();
		if(x < moveToX) {
			if(moveToX - x < dx) {
				x += (moveToX - x);
			}
			else {
				x += dx;
			}
		}
		else if(x > moveToX) {
			if(x - moveToX < dx) {
				x -= (x - moveToX);
			}
			else {
				x -= dx;
			}
		}

		batch.begin();
		batch.draw(animation.getKeyFrame(elapsedTime, true), x, y, 500, 500);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		basketballAtlas.dispose();
	}
}
