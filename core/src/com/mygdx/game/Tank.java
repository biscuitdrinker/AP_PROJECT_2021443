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
import java.io.Serializable;

public class Tank implements Serializable {

    protected transient int angle;

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    protected transient int power;

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    protected transient Body body;

    protected float x,y,speed,velx;

    protected transient BodyDef bodyDef;

    protected transient float width,height;
    protected double health;
    protected double fuel;

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

        this.health=100;
        this.fuel=200;
        this.hehe=new TextureRegion(tankimg);
        this.power=70;
        this.angle=300;




    }
    public  void update(SpriteBatch batch){
        this.x=body.getPosition().x;
        this.y=body.getPosition().y;
        batch.draw(hehe,body.getPosition().x,body.getPosition().y,0,0,45,30,1,1,(float)Math.toDegrees(body.getAngle()));
        if(this.fuel>0) {
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                velx = 10;
                this.fuel-=1;
            } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                velx = -10;
                this.fuel -= 1;
            } else {
                velx = 0;
            }
        }else{
            velx=0;
        }
//        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
//            Missile missile=new Missile(this);
//            velx=0;
//            missile.update(GameScreen.game.getBatch());
//        }
        if(this.health<0){
            GameScreen.game.getFont().draw(GameScreen.game.getBatch(),  "GAME OVER\n PLAYER 2 WINS ", 300, 400);
            GameScreen.setWait(true);

            // GameScreen.game.getFont().getData().setScale(2f,2f);
            GameScreen.getCurrent().dispose();
            GameScreen.game.setScreen(new MainMenu(GameScreen.game));




        }

        body.setLinearVelocity(velx,0);




    }
    int[] xpoints={0,12,12,24,24,36};
    int[]ypoints={0,12,18,18,12,0};
    protected float[] vertices={0,0,0,12,12,12,12,20,24,20,24,12,36,12,36,0};


    public void render(World world){
        body=world.createBody(bodyDef);
        PolygonShape tanky=new PolygonShape();
        tanky.set(vertices);
        body.setGravityScale(2);
        body.createFixture(tanky,1000).setUserData("Tanky");




    }


}
