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


    private Tank2 tank2;
    private Terrain land;

    public Tank2 getTank2() {
        return tank2;
    }

    public void setTank2(Tank2 tank2) {
        this.tank2 = tank2;
    }

    public Terrain getLand() {
        return land;
    }

    public void setLand(Terrain land) {
        this.land = land;
    }

    public Tankstars getGame() {
        return game;
    }

    public Texture getDropImage() {
        return dropImage;
    }

    public void setDropImage(Texture dropImage) {
        this.dropImage = dropImage;
    }

    public Texture getTankImage() {
        return TankImage;
    }

    public void setTankImage(Texture tankImage) {
        TankImage = tankImage;
    }

    public Texture getTankImage2() {
        return TankImage2;
    }

    public void setTankImage2(Texture tankImage2) {
        TankImage2 = tankImage2;
    }

    public Texture getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(Texture backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public TextureRegion getBackgroundTexture() {
        return backgroundTexture;
    }

    public void setBackgroundTexture(TextureRegion backgroundTexture) {
        this.backgroundTexture = backgroundTexture;
    }

    public Tank getTank() {
        return tank;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }

    public long getLastDropTime() {
        return lastDropTime;
    }

    public void setLastDropTime(long lastDropTime) {
        this.lastDropTime = lastDropTime;
    }

    public int getDropsGathered() {
        return dropsGathered;
    }

    public void setDropsGathered(int dropsGathered) {
        this.dropsGathered = dropsGathered;
    }

    private final Tankstars game;

    private Texture dropImage;
    private Texture TankImage;

    private Texture TankImage2;

    private Texture backgroundImage;
    private TextureRegion backgroundTexture;


    private Tank tank;
    private long lastDropTime;
    private int dropsGathered;

    public GameScreen(final Tankstars game) {
        this.game = game;

        // load the images for the droplet and the bucket, 64x64 pixels each
        dropImage = new Texture(Gdx.files.internal("drop.png"));
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
        game.camera = new OrthographicCamera();
       game.camera.setToOrtho(false, 800, 480);

        // create a Rectangle to logically represent the bucket
        tank = new Tank(TankImage);
        tank2= new Tank2(TankImage2);

        // bottom left corner of the bucket is 20 pixels above
        // the bottom screen edge



        // create the raindrops array and spawn the first raindrop
//        raindrops = new Array<Rectangle>();

    }



    @Override
    public void render(float delta) {
        // clear the screen with a dark blue color. The
        // arguments to clear are the red, green
        // blue and alpha component in the range [0,1]
        // of the color to be used to clear the screen.
        ScreenUtils.clear(0, 0, 0.2f, 1);

        // tell the camera to update its matrices.
        game.camera.update();

        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        game.batch.setProjectionMatrix(game.camera.combined);

        // begin a new batch and draw the bucket and
        // all drops

        game.batch.begin();
        game.batch.draw(backgroundTexture, 0,0, 800, 480);
        tank.update(game.batch);
        tank2.update(game.batch);



        game.font.draw(game.batch, "Drops Collected: " + dropsGathered, 0, 480);


     // game.batch.draw(dropImage, 100, 100, 45, 30);

       game.box2DDebugRenderer.render(game.world,game.camera.combined);


        game.batch.end();
        game.world.step(1/60f,6,2);
        if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
            dispose();
            game.setScreen(new pause(game));

        }

        // process user input
//        if (Gdx.input.isTouched()) {
//            Vector3 touchPos = new Vector3();
//            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
//            camera.unproject(touchPos);
//            tank.x = touchPos.x - 64 / 2;
//        }
//        if (Gdx.input.isKeyPressed(Keys.LEFT))
//            tank.x -= 200 * Gdx.graphics.getDeltaTime();
//        if (Gdx.input.isKeyPressed(Keys.RIGHT))
//            bucket.x += 200 * Gdx.graphics.getDeltaTime();
//
//        // make sure the bucket stays within the screen bounds
//        if (bucket.x < 0)
//            bucket.x = 0;
//        if (bucket.x > 800 - 64)
//            bucket.x = 800 - 64;

        // check if we need to create a new raindrop

        // move the raindrops, remove any that are beneath the bottom edge of
        // the screen or that hit the bucket. In the later case we increase the
        // value our drops counter and add a sound effect.
       //Iterator<Rectangle> iter = raindrops.iterator();
//        while (iter.hasNext()) {
//            Rectangle raindrop = iter.next();
//            raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
//            if (raindrop.y + 64 < 0)
//                iter.remove();
////            if (raindrop.overlaps(bucket)) {
////                dropsGathered++;
////               // dropSound.play();
////                iter.remove();
////            }
//        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        // start the playback of the background music
        // when the screen is shown
       // rainMusic.play();
        land.render(game.world);

        tank.render(game.world);
        tank2.render(game.world);

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
