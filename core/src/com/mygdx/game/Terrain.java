package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.World;

public class Terrain {
    private Body body;
    private Texture terr;

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Texture getTerr() {
        return terr;
    }

    public void setTerr(Texture terr) {
        this.terr = terr;
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

    static float[] vertices;




    private BodyDef bodyDef;

    private float width;



    Terrain(){

        this.bodyDef=new BodyDef();
       bodyDef.type= BodyDef.BodyType.StaticBody;
       bodyDef.position.set(0,0);
       vertices=new float[800];
       int index=-1;
       for(float x=0;x<19.97;x+=0.05){
           vertices[++index]=100*x;
           vertices[++index]= 150  + ((float) (-0.143 * Math.sin(1.75 * (x* 1.73)) - 0.180 * Math.sin(1.96 * (x+2.98)) - 0.012 * Math.sin(6.23 * (x+0.56)) + 0.088 * Math.sin(7.07 * (x+4.63))) * 100);


       }





    }

    public void render(World world){
        body= world.createBody(bodyDef);
        ChainShape chain=new ChainShape();
        chain.createChain(vertices);
        body.createFixture(chain,100000);


    }
}
