package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;


public class Tankstars extends Game {

    private SpriteBatch batch;

    private OrthographicCamera camera;
    private  BitmapFont font;

    private World world;

    public SpriteBatch getBatch() {
        return batch;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

    public BitmapFont getFont() {
        return font;
    }

    public void setFont(BitmapFont font) {
        this.font = font;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public Box2DDebugRenderer getBox2DDebugRenderer() {
        return box2DDebugRenderer;
    }

    public void setBox2DDebugRenderer(Box2DDebugRenderer box2DDebugRenderer) {
        this.box2DDebugRenderer = box2DDebugRenderer;
    }

    private Box2DDebugRenderer box2DDebugRenderer;




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

