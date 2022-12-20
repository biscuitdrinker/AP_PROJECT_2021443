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

public class Missile2 {
    private static boolean destroy2;

    public boolean isDestroy2() {
        return destroy2;
    }


    private boolean destroyed;


    private static boolean istank;
    public static void setDestroy2(boolean destroy2,boolean istank) {
        Missile2.istank=istank;
        Missile2.destroy2 = destroy2;
    }

    public boolean isDestroyed() {
        return destroyed;
    }








    private Tank tank=GameScreen.tank;

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

    Texture missileimg=new Texture(Gdx.files.internal("missile2.png"));
    public Missile2(){

        this.bodyDef=new BodyDef();

        bodyDef.type= BodyDef.BodyType.DynamicBody;

        this.destroyed=false;
        destroy2=false;


        this.hehe=new TextureRegion(missileimg);





    }
    public  void update(SpriteBatch batch){
        if(this.destroyed==false) {


            batch.draw(hehe, body3.getPosition().x, body3.getPosition().y, 0, 0, 30, 15, 1, 1, (float) Math.toDegrees(body3.getAngle()));

            if (destroy2==true ) {
                GameScreen.game.getWorld().destroyBody(body3);
                if(Missile2.istank==true) {
                    tank2.setHealth(tank2.getHealth() - 30);
                    Missile2.istank=false;
                }
                this.destroyed=true;
                destroy2=false;


            }




    }

    private float[] vertices={0,0,0,12,30,12,30,0};


    public void render(World world){
        bodyDef.position.set(tank2.getX(),tank2.getY()+70);
        body3=world.createBody(bodyDef);
        PolygonShape missy=new PolygonShape();
        missy.set(vertices);


        body3.createFixture(missy,100);
        Vector2 traj = new Vector2(0, tank2.getPower());
        traj.rotateDeg(tank2.getAngle());
        body3.setLinearVelocity(traj);
        body3.setTransform(body3.getPosition(), (float)Math.atan(body3.getLinearVelocity().y / body3.getLinearVelocity().x));




    }


}



