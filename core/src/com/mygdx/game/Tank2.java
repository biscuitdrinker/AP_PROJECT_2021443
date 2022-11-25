package com.mygdx.game;

import com.badlogic.gdx.Gdx;
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

    private Body body;





    private float x,y,speed,velx;

    private BodyDef bodyDef;

    private float width,height;
    private double health;
    private double fuel;

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
        batch.draw(hehe,body.getPosition().x,body.getPosition().y,45,30,45,30,1,1,(float)Math.toDegrees(body.getAngle()));


    }
    int[] xpoints={0,12,12,24,24,36};
    int[]ypoints={0,12,18,18,12,0};
    private float[] vertices={0,0,0,12,12,12,12,20,24,20,24,12,36,12,36,0};

    public void render(World world){
        body=world.createBody(bodyDef);
        PolygonShape tanky=new PolygonShape();
        tanky.set(vertices);
        body.createFixture(tanky,1000);




    }


}

