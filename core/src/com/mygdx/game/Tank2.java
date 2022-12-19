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
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class Tank2 {

    private Body body1;





    private float x,y,speed,velx;

    private BodyDef bodyDef;

    private float width,height;
    private double health;

    public Body getBody1() {
        return body1;
    }

    public void setBody1(Body body1) {
        this.body1 = body1;
    }

    public void setX(float x) {
        this.x = x;
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

    private double fuel;
    public float getX() {
        return body1.getPosition().x;
    }
    public float getY() {
        return body1.getPosition().y;
    }


    TextureRegion hehe;

    Texture tankimg;




    public Tank2(Texture tankimg){

        this.bodyDef=new BodyDef();
        bodyDef.type= BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(700,Terrain.vertices[701]+15);

        this.health=1000;
        this.fuel=1000;
        this.hehe=new TextureRegion(tankimg);
        hehe.flip(true,false);
        this.speed=4;
        this.width=45;
        this.height=30;



    }
    public  void update(SpriteBatch batch){
        batch.draw(hehe,body1.getPosition().x-10,body1.getPosition().y,5,0,45,30,1,1,(float)Math.toDegrees(body1.getAngle()));
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            velx=-10;

        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D)){
            velx=10;
        }else{
            velx=0;
        }
        body1.setLinearVelocity(velx,0);

    }
    int[] xpoints={0,12,12,24,24,36};
    int[]ypoints={0,12,18,18,12,0};
    private float[] vertices={0,0,0,12,12,12,12,20,24,20,24,12,36,12,36,0};

    public void render(World world){
        body1=world.createBody(bodyDef);
        PolygonShape tanky=new PolygonShape();
        tanky.set(vertices);
        body1.setGravityScale(2);
        body1.createFixture(tanky,1000);




    }


}

