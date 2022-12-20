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

import java.util.Iterator;

public class GameScreen implements Screen {





    private Missile2 missile2;
    private Terrain land;
    static Tankstars game;

    Texture dropImage;
    Texture TankImage;

    Texture TankImage2;

    private Texture backgroundImage;
    private TextureRegion backgroundTexture;
    Sound dropSound;
    Music rainMusic;

    static boolean ismissile;
    Array<Rectangle> raindrops;
    long lastDropTime;
    int dropsGathered;
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

    private Missile missile;
    public GameScreen(final Tankstars game) {
        this.game = game;

        // load the images for the droplet and the bucket, 64x64 pixels each

        TankImage = new Texture(Gdx.files.internal("tank1.png"));
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
        missile=new Missile(tank);
        missile2=new Missile2();
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
        game.getFont().draw(game.getBatch(), "PLAYER 2", 720, 470);
        game.getFont().draw(game.getBatch(), "HEALTH:"+ tank.getHealth(), 0, 450);
        game.getFont().draw(game.getBatch(), "HEALTH:"+ tank2.getHealth(), 720, 450);
        game.getFont().draw(game.getBatch(), "POWER "+ tank.getPower(), 0, 430);
        game.getFont().draw(game.getBatch(), "POWER "+ tank2.getPower(), 720, 430);
     game.getFont().draw(game.getBatch(), "ANGLE"+ tank.getAngle(), 0, 410);
     game.getFont().draw(game.getBatch(), "ANGLE"+ tank2.getAngle(), 720, 430);








        tank.update(game.getBatch());
        tank2.update(game.getBatch());
        missile.update(game.getBatch());
        missile2.update(game.getBatch());





        // game.batch.draw(dropImage, 100, 100, 45, 30);

        game.getBox2DDebugRenderer().render(game.getWorld(), game.getCamera().combined);


        game.getBatch().end();
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
            dispose();
            game.setScreen(new pause(game));

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





    }

}
