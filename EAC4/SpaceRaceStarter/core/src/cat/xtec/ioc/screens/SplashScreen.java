package cat.xtec.ioc.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import cat.xtec.ioc.SpaceRace;
import cat.xtec.ioc.helpers.AssetManager;
import cat.xtec.ioc.objects.Spacecraft;
import cat.xtec.ioc.utils.Methods;
import cat.xtec.ioc.utils.Settings;


public class SplashScreen implements Screen {

    private Stage stage;
    private SpaceRace game;

    private Label.LabelStyle textStyle;
    private Label textLbl;


    public SplashScreen(SpaceRace game) {

        this.game = game;

        // Creem la càmera de les dimensions del joc
        OrthographicCamera camera = new OrthographicCamera(Settings.GAME_WIDTH, Settings.GAME_HEIGHT);
        // Posant el paràmetre a true configurem la càmera per a
        // que faci servir el sistema de coordenades Y-Down
        camera.setToOrtho(true);

        // Creem el viewport amb les mateixes dimensions que la càmera
        StretchViewport viewport = new StretchViewport(Settings.GAME_WIDTH, Settings.GAME_HEIGHT, camera);

        // Creem l'stage i assginem el viewport
        stage = new Stage(viewport);

        // Afegim el fons
        stage.addActor(new Image(AssetManager.background));

        // Creem l'estil de l'etiqueta i l'etiqueta
        textStyle = new Label.LabelStyle(AssetManager.font, null);
        textLbl = new Label("The Witch's Flight", textStyle);
        textLbl.setColor(Color.GRAY);

        // Creem el contenidor necessari per aplicar-li les accions
        Container container = new Container(textLbl);
        container.setTransform(true);
        container.center();
        container.setPosition(Settings.GAME_WIDTH / 2, Settings.GAME_HEIGHT / 2);

        // Afegim les accions de escalar: primer es fa gran i després torna a l'estat original ininterrompudament
        //TODO 2.a. Titulo aparece y desaparece.
        container.addAction(Actions.repeat(RepeatAction.FOREVER, Actions.sequence(

                //TODO 2.a. Escalado  a 1.5f y visible (pasa de alpha 0 a alpha 1)
                Actions.parallel(Actions.scaleTo(1.5f, 1.5f, 1),Actions.alpha(1.0f,1)),

                //TODO 2.a. Escalado a 1 y oculto (pasa de alpha 1 a alpha 0)
                Actions.parallel(Actions.scaleTo(1, 1, 1),Actions.alpha(0.0f,1)))));

        stage.addActor(container);

        /*TODO 2.b. Posicion Inicial y final del pj = Random en app (Limite SPACECRAFT_HEIGHT)
         Asignar las texturas según si las posiciones son iguales o distintas
         El pj subira o bajara */

        float y_start=Methods.randomFloat(0.0f,Settings.GAME_HEIGHT-Settings.SPACECRAFT_HEIGHT);
        float y_end=Methods.randomFloat(0.0f,Settings.GAME_HEIGHT-Settings.SPACECRAFT_HEIGHT);

        // Creem la imatge de la nau i li assignem el moviment en horitzontal
        //Image spacecraft = new Image(AssetManager.spacecraft);
        //float y = Settings.GAME_HEIGHT / 2 + textLbl.getHeight();

        //TODO 2.b. Segun la relacion entre las posiciones, asignamos la textura del caracter
        Image spacecraft=null;
        if(y_start > y_end){
            spacecraft=new Image(AssetManager.spacecraftDown);
        } else if(y_start < y_end){
            spacecraft=new Image(AssetManager.spacecraftUp);
        } else {
            spacecraft=new Image(AssetManager.spacecraft);
        }

        //TODO 2.b. Movimiento de "y_start" a "y_end"
        //spacecraft.addAction(Actions.repeat(RepeatAction.FOREVER, Actions.sequence(Actions.moveTo(0 - spacecraft.getWidth(), y), Actions.moveTo(Settings.GAME_WIDTH, y, 5))));
        spacecraft.addAction(Actions.repeat(RepeatAction.FOREVER,Actions.sequence(
                Actions.moveTo(0 - spacecraft.getWidth(),y_start),
                Actions.moveTo(Settings.GAME_WIDTH, y_end, 10))));

        stage.addActor(spacecraft);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        stage.draw();
        stage.act(delta);

        // Si es fa clic en la pantalla, canviem la pantalla
        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(stage.getBatch(), stage.getViewport()));
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
