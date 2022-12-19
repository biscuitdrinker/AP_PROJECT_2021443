package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Missile {

    private int angle;

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    private Tank tank;

    private Tank2 tank2=GameScreen.tank2;
    private Body body3;

    private float x,y,speed,velx;

    private BodyDef bodyDef;

    private float width,height;


    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getVelx() {
        return velx;
    }

    public void setVelx(float velx) {
        this.velx = velx;
    }

    public BodyDef getBodyDef() {
        return bodyDef;
    }

    public void setBodyDef(BodyDef bodyDef) {
        this.bodyDef = bodyDef;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }



    public float[] getVertices() {
        return vertices;
    }

    public void setVertices(float[] vertices) {
        this.vertices = vertices;
    }

    TextureRegion hehe;

    Texture missileimg=new Texture(Gdx.files.internal("missile.png"));
    public Missile(Tank tank){

        this.bodyDef=new BodyDef();

        bodyDef.type= BodyDef.BodyType.DynamicBody;
        this.tank=tank;



        this.hehe=new TextureRegion(missileimg);

        this.speed=4;
        this.width=45;
        this.height=30;



    }
    public  void update(SpriteBatch batch){
        batch.draw(hehe,body3.getPosition().x,body3.getPosition().y,0,0,30,15,1,1,(float)Math.toDegrees(body3.getAngle()));
        if(body3.getPosition().x-tank2.getX()<36 && body3.getPosition().y-tank2.getY()<15){
            body3.setLinearVelocity(0,50);
            tank2.setHealth(tank2.getHealth()-30);
        }




    }

    private float[] vertices={0,0,0,12,30,12,30,0};


    public void render(World world){
        bodyDef.position.set(tank.getX(),tank.getY()+70);
        body3=world.createBody(bodyDef);
        PolygonShape missy=new PolygonShape();
        missy.set(vertices);


        body3.createFixture(missy,100);
        Vector2 traj = new Vector2(0, 70);
        traj.rotateDeg(300);
        body3.setLinearVelocity(traj);
        body3.setTransform(body3.getPosition(), (float)Math.atan(body3.getLinearVelocity().y / body3.getLinearVelocity().x));




    }


}



