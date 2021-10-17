package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Ball {

    public Body basketball;

    public Ball(Batch batch_, World world, float x, float y) {

        //DEFINE BODY
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(x, y));//given pixel coords but needs world coords
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        //CREATE BODY
        basketball = world.createBody(bodyDef);

        //CREATE SHAPE
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(.005f);

        //CREATE FIXTURE
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = .1f;
        fixtureDef.restitution = .8f;
        fixtureDef.friction = .5f;
        fixtureDef.shape = circleShape;

        //ATTACH FIXTURE TO BODY
        basketball.createFixture(fixtureDef);

        img = new Texture(Gdx.files.internal("static_ball.png"));
        batch = batch_;
    }

    public void applyForce(float forceX, float forceY) {
        basketball.applyForceToCenter(forceX, forceY, true);
    }

    public boolean display() {
        if(basketball.getPosition().y > -200) {
            batch.draw(img, basketball.getPosition().x, basketball.getPosition().y, 500, 500);
            return true;
        }
        else {
            basketball.setTransform(0, -200, 0);
            return false;
        }
    }

    private Batch batch;
    private Texture img;
}
