package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;


public class Tankstars extends Game {

    public SpriteBatch batch;

    OrthographicCamera camera;
    public BitmapFont font;

    World world;

    Box2DDebugRenderer box2DDebugRenderer;




    public void create() {
        batch = new SpriteBatch();
        world= new World(new Vector2(0,-9.8f),true);
        font = new BitmapFont(); // use libGDX's default Arial font
        this.box2DDebugRenderer=new Box2DDebugRenderer();
        this.camera= new OrthographicCamera();

        this.setScreen(new MainMenu(this));


    }

    public void render() {
        super.render(); // important!
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
        world.dispose();
    }

}

