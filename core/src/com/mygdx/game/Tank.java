package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.awt.*;

public class Tank {

    private int angle;

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    private int power;

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    private Body body;

    private float x,y,speed,velx;

    private BodyDef bodyDef;

    private float width,height;
    private double health;
    private double fuel;

    public float getX() {
        return body.getPosition().x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return body.getPosition().y;
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

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public float[] getVertices() {
        return vertices;
    }

    public void setVertices(float[] vertices) {
        this.vertices = vertices;
    }

    TextureRegion hehe;

    Texture tankimg;




    public Tank(Texture tankimg){

        this.bodyDef=new BodyDef();
        bodyDef.type= BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(Terrain.vertices[38]+15,Terrain.vertices[39]+20);

        this.health=1000;
        this.fuel=1000;
        this.hehe=new TextureRegion(tankimg);

        this.speed=4;
        this.width=45;
        this.height=30;



    }
  public  void update(SpriteBatch batch){
      batch.draw(hehe,body.getPosition().x,body.getPosition().y,0,0,45,30,1,1,(float)Math.toDegrees(body.getAngle()));
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            velx=10;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            velx=-10;
        }else{
            velx=0;
        }
//        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
//            Missile missile=new Missile(this);
//            velx=0;
//            missile.update(GameScreen.game.getBatch());
//        }
        body.setLinearVelocity(velx,0);




    }
    int[] xpoints={0,12,12,24,24,36};
    int[]ypoints={0,12,18,18,12,0};
    private float[] vertices={0,0,0,12,12,12,12,20,24,20,24,12,36,12,36,0};


    public void render(World world){
        body=world.createBody(bodyDef);
        PolygonShape tanky=new PolygonShape();
        tanky.set(vertices);
        body.setGravityScale(2);
        body.createFixture(tanky,1000).setUserData("Tanky");




    }


}
