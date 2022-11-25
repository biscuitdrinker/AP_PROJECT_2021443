package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.w3c.dom.Text;

import java.awt.*;

public class pause implements Screen {

    private Stage stage;
    private final Tankstars game;
    private Texture backgroundImage;
    private TextureRegion backgroundTexture;
    public OrthographicCamera camera;

    private TextButton resumebutton;
    private TextButton newgamebutton;
    private TextButton savenquit;
    private  TextButton quit;

    public pause(final Tankstars game) {
        this.stage=new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(this.stage);
        this.game = game;
        backgroundImage = new Texture(Gdx.files.internal("bg6.png"));
        backgroundTexture = new TextureRegion(backgroundImage, 0, 150, 800, 400);
        Skin skin= new Skin(Gdx.files.internal("skin/quantum-horizon-ui.json"));

        this.newgamebutton=new TextButton("NEW GAME",skin);
        this.newgamebutton.setPosition(340,250);
        this.newgamebutton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
                dispose();
            }
        });

        this.resumebutton=new TextButton("RESUME GAME",skin);
        this.resumebutton.setPosition(340,200);
        this.resumebutton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
                dispose();
            }
        });

        this.savenquit=new TextButton("SAVE AND QUIT",skin);
        this.savenquit.setPosition(340,150);
        this.savenquit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenu(game));
                dispose();
            }
        });

        this.quit=new TextButton("QUIT",skin);
        this.quit.setPosition(340,100);
        this.quit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenu(game));
                dispose();
            }
        });
        //camera = new OrthographicCamera();
        game.camera.setToOrtho(false, 800, 400);
        this.stage.addActor(newgamebutton);
        this.stage.addActor(resumebutton);
        this.stage.addActor(quit);
        this.stage.addActor(savenquit);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);

        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);

        game.batch.begin();
        game.batch.draw(backgroundTexture, 0,-50, 800, 480);

        game.font.getData().setScale(2,2);
        game.font.draw(game.batch, "YOUR GAME IS PAUSED", 240, 300);
        game.font.getData().setScale(1,1);


//        game.font.draw(game.batch, "1) RESUME", 340, 250);
//        game.font.draw(game.batch, "2) NEW GAME", 340, 200);
//        game.font.draw(game.batch, "3) SAVE AND QUIT", 340, 150);
//        game.font.draw(game.batch, "4) QUIT", 340, 100);


        game.batch.end();
        this.stage.act(delta);
        this.stage.draw();

        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            game.setScreen(new GameScreen(game));
            dispose();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
            game.setScreen(new MainMenu(game));
            dispose();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)) {
            game.setScreen(new MainMenu(game));
            dispose();
        }


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
