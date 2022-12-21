package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import static java.lang.System.out;

public class GameScreen implements Screen, Serializable {


    private static GameScreen current;

    public static GameScreen getCurrent() {
        return current;
    }

    public static void setCurrent(GameScreen current) {
        GameScreen.current = current;
    }

    public static boolean isWait() {
        return wait;
    }

    public static void setWait(boolean wait) {
        GameScreen.wait = wait;
    }

    private static boolean wait;
    private static boolean isfired;

    public static boolean isIsfired() {
        return isfired;
    }

    public static void setIsfired(boolean isfired) {
        GameScreen.isfired = isfired;
    }

    //private Missile2 missile2;
    private Terrain land;
    static Tankstars game;

    Texture dropImage;
    Texture TankImage;

    Texture TankImage2;

    private Texture backgroundImage;
    private TextureRegion backgroundTexture;
    transient Sound dropSound;
    transient Music rainMusic;

    static boolean ismissile;
    transient Array<Rectangle> raindrops;
    transient  long lastDropTime;
    transient int dropsGathered;
    static Tank2 tank2;

    public  Tank2 getTank2() {
        return tank2;
    }

    public void setTank2(Tank2 tank2) {
        this.tank2 = tank2;
    }

    public Terrain getLand() {
        return land;
    }
    static Tank tank;
    public void setLand(Terrain land) {
        this.land = land;
    }

    public Tank getTank() {
        return tank;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }

    public Missile getMissile() {
        return missile;
    }

    public void setMissile(Missile missile) {
        this.missile = missile;
    }

    private transient Missile missile;

    private  transient Missile missile2;

    public Missile getMissile2() {
        return missile2;
    }

    public void setMissile2(Missile missile2) {
        this.missile2 = missile2;
    }
    private transient Texture Tankimage;

    public GameScreen(final Tankstars game,Texture TankImage) {
        this.game = game;
        current=this;
        // load the images for the droplet and the bucket, 64x64 pixels each
        this.TankImage=TankImage;
        //TankImage = new Texture(Gdx.files.internal("tank1.png"));
        TankImage2 = new Texture(Gdx.files.internal("TANK-2.png"));
        backgroundImage = new Texture(Gdx.files.internal("bg4.png"));
        backgroundTexture = new TextureRegion(backgroundImage, -300, -200, 1920, 1229);
        this.land=new Terrain();

        // load the drop sound effect and the rain background "music"
//        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
//        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
//        rainMusic.setLooping(true);

        // create the camera and the SpriteBatch
        game.setCamera(new OrthographicCamera()) ;
        game.getCamera().setToOrtho(false, 800, 480);

        // create a Rectangle to logically represent the bucket
        tank = new Tank(TankImage);
        tank2= new Tank2(TankImage2);
        missile=new Missile(tank,tank2);
        missile2=new Missile(tank2,tank);
        if(wait==true){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {

            }finally {
                game.setScreen(new MainMenu(game));
            }
        }
        //  missile2=new Missile2();
        // missile.setDestroyed(false);



        // bottom left corner of the bucket is 20 pixels above
        // the bottom screen edge



        // create the raindrops array and spawn the first raindrop


    }

    private void spawnRaindrop() {
        Rectangle raindrop = new Rectangle();
        raindrop.x = MathUtils.random(0, 800 - 64);
        raindrop.y = 480;
        raindrop.width = 64;
        raindrop.height = 64;
        raindrops.add(raindrop);
        lastDropTime = TimeUtils.nanoTime();
    }



    @Override
    public void render(float delta) {
        // clear the screen with a dark blue color. The
        // arguments to clear are the red, green
        // blue and alpha component in the range [0,1]
        // of the color to be used to clear the screen.
        ScreenUtils.clear(0, 0, 0.2f, 1);

        // tell the camera to update its matrices.
        game.getCamera().update();

        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        game.getBatch().setProjectionMatrix(game.getCamera().combined);

        // begin a new batch and draw the bucket and
        // all drops

        game.getBatch().begin();
        game.getBatch().draw(backgroundTexture, 0, 0, 800, 480);
        game.getFont().draw(game.getBatch(), "PLAYER 1", 0, 470);
        game.getFont().draw(game.getBatch(), "PLAYER 2", 700, 470);
        game.getFont().draw(game.getBatch(), "HEALTH "+ tank.getHealth(), 0, 450);
        game.getFont().draw(game.getBatch(), "HEALTH "+ tank2.getHealth(), 700, 450);
        game.getFont().draw(game.getBatch(), "POWER "+ tank.getPower(), 0, 430);
        game.getFont().draw(game.getBatch(), "POWER "+ tank2.getPower(), 700, 430);
        game.getFont().draw(game.getBatch(), "ANGLE "+ tank.getAngle(), 0, 410);
        game.getFont().draw(game.getBatch(), "ANGLE "+ tank2.getAngle(), 700, 410);
        game.getFont().draw(game.getBatch(), "FUEL "+ tank.getFuel(), 0, 390);
        game.getFont().draw(game.getBatch(), "FUEL "+ tank2.getFuel(), 700, 390);








        tank.update(game.getBatch());
        tank2.update(game.getBatch());

        missile.update(game.getBatch());

        missile2.update(game.getBatch());





        // game.batch.draw(dropImage, 100, 100, 45, 30);



        game.getBatch().end();

        game.getBox2DDebugRenderer().render(game.getWorld(), game.getCamera().combined);
        float accumulator = 0.1f;


        // fixed time step
        // max frame time to avoid spiral of death (on slow devices)
        float frameTime = Math.min(delta, 0.25f);
        accumulator += frameTime;
        while (accumulator >= 1 / 60f) {
            game.getWorld().step(1 / 60f, 6, 2);

            accumulator -= 1 / 60f;
        }

        if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
            current=this;
            game.setScreen(new pause(game));

        }
        else if(Gdx.input.isKeyJustPressed(Keys.ENTER)){
            if(isfired==false) {
                missile.setDestroyed(false);

                missile.render(game.getWorld());
            }
        }
        else if(Gdx.input.isKeyJustPressed(Keys.SHIFT_LEFT)){
            if(isfired==false) {
                missile2.setDestroyed(false);

                missile2.render(game.getWorld());
            }
        }
        else if(Gdx.input.isKeyPressed(Keys.UP)){
            tank.setPower(tank.getPower()+1);
        }
        else if(Gdx.input.isKeyPressed(Keys.DOWN)){
            tank.setPower(tank.getPower()-1);
        }
        else if(Gdx.input.isKeyPressed(Keys.O)){
            tank.setAngle(tank.getAngle()+1);
        }
        else if(Gdx.input.isKeyPressed(Keys.L)){
            tank.setAngle(tank.getAngle()-1);
        }
        else if(Gdx.input.isKeyPressed(Keys.W)){
            tank2.setPower(tank2.getPower()+1);
        }
        else if(Gdx.input.isKeyPressed(Keys.S)){
            tank2.setPower(tank2.getPower()-1);
        }
        else if(Gdx.input.isKeyPressed(Keys.I)){
            tank2.setAngle(tank2.getAngle()+1);
        }
        else if(Gdx.input.isKeyPressed(Keys.K)){
            tank2.setAngle(tank2.getAngle()-1);
        }



    }
    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        // start the playback of the background music
        // when the screen is shown
        // rainMusic.play();
        land.render(game.getWorld());

        tank.render(game.getWorld());
        tank2.render(game.getWorld());
        missile2.render(game.getWorld());
        missile.render(game.getWorld());

    }


    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {

        TankImage.dispose();
        TankImage2.dispose();
        game.getWorld().destroyBody(tank.body);
        game.getWorld().destroyBody(tank2.body);




    }
    public static void writes(Serializable st, int i) {
        try {
            ObjectOutputStream  out = new ObjectOutputStream(Files.newOutputStream(Paths.get("output" + i)));
            out.writeObject(st);
            out.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static GameScreen reads(int i) {
        GameScreen t;
        try {
            ObjectInputStream  inp = new ObjectInputStream(Files.newInputStream(Paths.get("output" + i)));
            t = (GameScreen) inp.readObject();
            inp.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return t;
    }
}


