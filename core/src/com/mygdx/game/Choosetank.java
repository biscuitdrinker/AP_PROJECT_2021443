package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class Choosetank implements Screen {

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Button getNewGamebutton() {
        return NewGamebutton;
    }

    public void setNewGamebutton(Button newGamebutton) {
        NewGamebutton = newGamebutton;
    }

    public Button getLoadgamebutton() {
        return Loadgamebutton;
    }

    public void setLoadgamebutton(Button loadgamebutton) {
        Loadgamebutton = loadgamebutton;
    }

    public Button getQuitbutton() {
        return quitbutton;
    }

    public void setQuitbutton(Button quitbutton) {
        this.quitbutton = quitbutton;
    }

    public Tankstars getGame() {
        return game;
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

    private Stage stage;

    private Button NewGamebutton;
    private Button Loadgamebutton;
    private Button quitbutton;
    private final Tankstars game;
    private Texture backgroundImage;
    private TextureRegion backgroundTexture;
    private OrthographicCamera camera;





    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

    public Choosetank(final Tankstars game) {
        this.game = game;
        stage= new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        backgroundImage = new Texture(Gdx.files.internal("img_1.png"));
        Skin skin= new Skin(Gdx.files.internal("skin/quantum-horizon-ui.json"));
        this.NewGamebutton= new TextButton("TANK1", skin);
        // this.NewGamebutton.setSize(100,50);
        this.NewGamebutton.setPosition(60,300);
        this.NewGamebutton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game,new Texture(Gdx.files.internal("tank1.png"))));
            }
        });

        this.Loadgamebutton= new TextButton("TANK2", skin);
        //  this.Loadgamebutton.setSize(50,20);
        this.Loadgamebutton.setPosition(60,200);
        this.Loadgamebutton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game,new Texture(Gdx.files.internal("TANK-2.png"))));

            }
        });


        this.quitbutton= new TextButton("TANK3", skin);
        //  this.quitbutton.setSize(50,20);
        this.quitbutton.setPosition(60,100);
        this.quitbutton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game,new Texture(Gdx.files.internal("TANK-3.png"))));

            }
        });



        backgroundTexture = new TextureRegion(backgroundImage, -120, 0, 800, 400);
        // camera = new OrthographicCamera();
        game.getCamera().setToOrtho(false, 800, 480);
        stage.addActor(NewGamebutton);
        stage.addActor(Loadgamebutton);
        stage.addActor(quitbutton);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);

        game.getCamera().update();
        game.getBatch().setProjectionMatrix(game.getCamera().combined);

        game.getBatch().begin();
        game.getBatch().draw(backgroundTexture, 0,0, 800, 480);
        game.getBatch().draw(new Texture(Gdx.files.internal("tank1.png")),240,300,150,100);
        game.getBatch().draw(new Texture(Gdx.files.internal("TANK-2.png")),240,200,150,100);
        game.getBatch().draw(new Texture(Gdx.files.internal("TANK-3.png")),240,100,150,100);






//        game.font.draw(game.batch, "1) NEW GAME", 340, 140);
//        game.font.draw(game.batch, "2) LOAD GAME", 340, 100);
//        game.font.draw(game.batch,"3) EXIT GAME",340,60);

        game.getBatch().end();
        stage.act(delta);
        stage.draw();


//        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
//            game.setScreen(new GameScreen(game));
//            dispose();
//        }
//        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
//            game.setScreen(new GameScreen(game));
//            dispose();
//        }
//        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)){
//            Gdx.app.exit();
//        }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}
