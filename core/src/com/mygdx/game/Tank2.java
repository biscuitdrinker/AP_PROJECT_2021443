package com.mygdx.game;

import com.badlogic.gdx.Game;
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

import java.io.Serializable;

public class Tank2 extends Tank implements Serializable {

    protected transient Body body1;
    public Tank2(Texture tankimg){

        super(tankimg);
        bodyDef.position.set(700,Terrain.vertices[701]+15);
        hehe.flip(true,false);
        this.angle=80;


    }
    public  void update(SpriteBatch batch){
        this.x=body.getPosition().x;
        this.y=body.getPosition().y;
        batch.draw(hehe,body.getPosition().x-10,body.getPosition().y,5,0,45,30,1,1,(float)Math.toDegrees(body.getAngle()));
        if(this.fuel>0) {
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                velx = -10;
                this.fuel-=1;
            } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                velx = 10;
                this.fuel-=1;
            } else {
                velx = 0;
            }
        }else{
            velx=0;
        }
        if(this.health<0){
            GameScreen.game.getFont().draw(GameScreen.game.getBatch(),  "GAME OVER\n PLAYER 1 WINS ", 300, 400);
            GameScreen.setWait(true);
            GameScreen.getCurrent().dispose();

            // GameScreen.game.getFont().getData().setScale(2f,2f);
            GameScreen.game.setScreen(new MainMenu(GameScreen.game));



        }
        body.setLinearVelocity(velx,0);

    }

    protected static float[] vertices={0,0,0,12,12,12,12,20,24,20,24,12,36,12,36,0};

    public void render(World world){
        body=world.createBody(bodyDef);
        PolygonShape tanky=new PolygonShape();
        tanky.set(vertices);
        body.setGravityScale(2);
        body.createFixture(tanky,1000).setUserData("Tanky");




    }


}

