package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class MyGdxGame extends ApplicationAdapter {

	SpriteBatch batch;
	OrthographicCamera camera;
	ExtendViewport viewport;
	World world;
	Ball ball;
	private TextureAtlas basketballAtlas;
	Box2DDebugRenderer renderer;
	private Animation<TextureRegion> animation;
	private float elapsedTime = 0f;
	private int x, y;

	
	@Override
	public void create () {
		camera = new OrthographicCamera();
		viewport = new ExtendViewport(50, 50, camera);

		batch = new SpriteBatch();

		basketballAtlas = new TextureAtlas(Gdx.files.internal("Basketball.atlas"));
		animation = new Animation<TextureRegion>(.075f, basketballAtlas.getRegions());
		x = 30;
		y = 25;

		Gdx.input.setInputProcessor(new GestureDetector(new GestureDetector.GestureAdapter() {
			@Override
			public boolean fling(float velociyX, float velocityY, int button) {
				ball.applyForce(velociyX, velocityY);
				return true;
			}
		}));

		Box2D.init();
		renderer = new Box2DDebugRenderer();
		world = new World(new Vector2(0, -20), true);
		createWalls();
		ball = new Ball(batch, world, x, y);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);

		world.step(1f / 60f, 8 ,3);

		elapsedTime += Gdx.graphics.getDeltaTime();

		batch.begin();

		if(ball.display()) {//only display dribble animation if ball on the ground
			batch.draw(animation.getKeyFrame(elapsedTime, true), ball.basketball.getPosition().x, ball.basketball.getPosition().y, 10, 10);
		}

		batch.end();

		renderer.render(world, camera.combined);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		basketballAtlas.dispose();
		world.dispose();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height, true);
		batch.setProjectionMatrix(camera.combined);
	}

	public Body createGround() {
		BodyDef g = new BodyDef();
		g.type = BodyDef.BodyType.StaticBody;
		g.position.set(new Vector2(0, 0));
		Body body = world.createBody(g);
		PolygonShape shape = new PolygonShape();
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		shape.setAsBox(100, 0);
		body.createFixture(fixtureDef);
		shape.dispose();
		return body;
	}

	public Body createRightWall() {
		BodyDef g = new BodyDef();
		g.type = BodyDef.BodyType.StaticBody;
		g.position.set(new Vector2(80, 0));
		Body body = world.createBody(g);
		PolygonShape shape = new PolygonShape();
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		shape.setAsBox(0, 100);
		body.createFixture(fixtureDef);
		shape.dispose();
		return body;
	}

	public Body createLeftWall() {
		BodyDef g = new BodyDef();
		g.type = BodyDef.BodyType.StaticBody;
		g.position.set(new Vector2(-10, 0));
		Body body = world.createBody(g);
		PolygonShape shape = new PolygonShape();
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		shape.setAsBox(0, 100);
		body.createFixture(fixtureDef);
		shape.dispose();
		return body;
	}

	public Body createCeiling() {
		BodyDef g = new BodyDef();
		g.type = BodyDef.BodyType.StaticBody;
		g.position.set(new Vector2(0, 51));
		Body body = world.createBody(g);
		PolygonShape shape = new PolygonShape();
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		shape.setAsBox(100, 0);
		body.createFixture(fixtureDef);
		shape.dispose();
		return body;
	}

	public void createWalls() {
		createGround();
		createCeiling();
		createLeftWall();
		createRightWall();
	}
}
