package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MyGdxGame extends ApplicationAdapter {

	SpriteBatch batch;
	World world;
	Ball ball;
	Texture astronaut;
	private TextureAtlas basketballAtlas;
	private Animation<TextureRegion> animation;
	private float elapsedTime = 0f;
	private int x, y;
	int dx;

	
	@Override
	public void create () {
		batch = new SpriteBatch();

		astronaut = new Texture(Gdx.files.internal("amogus.jpeg"));
		basketballAtlas = new TextureAtlas(Gdx.files.internal("Basketball.atlas"));
		animation = new Animation<TextureRegion>(.075f, basketballAtlas.getRegions());
		dx = 10;
		x = 0;
		y = 0;

		Gdx.input.setInputProcessor(new GestureDetector(new GestureDetector.GestureAdapter() {
			@Override
			public boolean touchDown(float x, float y, int pointer, int button) {
				ball.applyForce(0, 100);
				return true;
			}
		}));

		Box2D.init();
		world = new World(new Vector2(0, -20), true);
		ball = new Ball(batch, world, x, y);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);

		world.step(1f / 60f, 8 ,3);

		elapsedTime += Gdx.graphics.getDeltaTime();

		batch.begin();

		batch.draw(astronaut, 500, 0);

		if(!ball.display()) {
			batch.draw(animation.getKeyFrame(elapsedTime, true), ball.basketball.getPosition().x, y, 500, 500);
		}

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		basketballAtlas.dispose();
		world.dispose();
	}
}
