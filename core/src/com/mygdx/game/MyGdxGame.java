package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {

	SpriteBatch batch;
	private TextureAtlas basketballAtlas;
	private Animation<TextureRegion> animation;
	private float elapsedTime = 0f;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		basketballAtlas = new TextureAtlas(Gdx.files.internal("Basketball.atlas"));
		animation = new Animation<TextureRegion>(.075f, basketballAtlas.getRegions());
	}

	@Override
	public void render () {
		elapsedTime += Gdx.graphics.getDeltaTime();
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		batch.draw(animation.getKeyFrame(elapsedTime, true), 50,50, 500, 500);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		basketballAtlas.dispose();
	}
}
