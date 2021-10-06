package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;

public class MyGdxGame extends ApplicationAdapter {

	SpriteBatch batch;
	private TextureAtlas animalAtlas;
	private Animation<TextureRegion> animation;
	private float elapsedTime = 0f;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		animalAtlas = new TextureAtlas(Gdx.files.internal("Animals.atlas"));
		animation = new Animation<TextureRegion>(1f, animalAtlas.getRegions());
	}

	@Override
	public void render () {
		elapsedTime += Gdx.graphics.getDeltaTime();
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(animation.getKeyFrame(elapsedTime, true), 0,0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		animalAtlas.dispose();
	}
}
