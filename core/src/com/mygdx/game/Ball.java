package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Ball {

    public Body basketball;

    public Ball(World world, float x, float y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(x, y));
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(.5f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = .1f;
        fixtureDef.restitution = .8f;
        fixtureDef.friction = .5f;
        fixtureDef.shape = circleShape;

        basketball = world.createBody(bodyDef);
        basketball.createFixture(fixtureDef);
        circleShape.dispose();
    }

    public void applyForce(Vector2 force, float forceX, float forceY) {
        basketball.applyForceToCenter(forceX, forceY, true);
    }
}
