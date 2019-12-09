package cat.xtec.ioc.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.Shape;
import java.math.RoundingMode;
import java.util.ArrayList;

import cat.xtec.ioc.helpers.AssetManager;
import cat.xtec.ioc.helpers.InputHandler;
import cat.xtec.ioc.objects.Asteroid;
import cat.xtec.ioc.objects.Coin;
import cat.xtec.ioc.objects.ScrollHandler;
import cat.xtec.ioc.objects.Spacecraft;
import cat.xtec.ioc.utils.Settings;

import static cat.xtec.ioc.helpers.AssetManager.font;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.addAction;


public class GameScreen implements Screen {

    // Els estats del joc
    //TODO 4 AÑADIR PAUSE a la lista de estados
    public enum GameState {

        READY, RUNNING, PAUSE, GAMEOVER

    }

    private GameState currentState;

    // Objectes necessaris
    private Stage stage;
    private Spacecraft spacecraft;
    private ScrollHandler scrollHandler;
    private Coin coin;

    // Encarregats de dibuixar elements per pantalla
    private ShapeRenderer shapeRenderer;
    private Batch batch;

    // Per controlar l'animació de l'explosió
    private float explosionTime = 0;

    // Preparem el textLayout per escriure text
    private GlyphLayout textLayout;

    //TODO 3.1 variables score
    //Score=Puntuacion
    public int score;
    //labelScore=label de score
    private Label labelScore;
    //Container para score
    private Container containerScore;

    //TODO 5 Variable highScore y prefs
    //High Score
    public int highScore;
    //Preferences
    private Preferences prefs;

    public GameScreen(Batch prevBatch, Viewport prevViewport) {

        // Iniciem la música
        AssetManager.music.play();

        // Creem el ShapeRenderer
        shapeRenderer = new ShapeRenderer();

        // Creem l'stage i assginem el viewport
        stage = new Stage(prevViewport, prevBatch);

        batch = stage.getBatch();

        // Creem la nau i la resta d'objectes
        spacecraft = new Spacecraft(Settings.SPACECRAFT_STARTX, Settings.SPACECRAFT_STARTY, Settings.SPACECRAFT_WIDTH, Settings.SPACECRAFT_HEIGHT);
        scrollHandler = new ScrollHandler();

        // Afegim els actors a l'stage
        stage.addActor(scrollHandler);
        stage.addActor(spacecraft);
        // Donem nom a l'Actor
        spacecraft.setName("spacecraft");

        //TODO 3.1 Label Score
        //TODO 3.1 Asignamos la fuente de Asset Manager a la label
        Label.LabelStyle labelTextStyle = new Label.LabelStyle(font,null);
        //TODO 3.1 Inicializar labelScore con el valor de score a "Score: 0"
        labelScore=new Label(String.valueOf(score),labelTextStyle);
        labelScore.setText("Score: "+ score);
        labelScore.setColor(Color.GRAY);
        //TODO 3.1 Inicializar Container de score y posicionarlo
        containerScore=new Container(labelScore);
        containerScore.setName("score");
        containerScore.left();
        containerScore.setTransform(true);
        containerScore.setPosition(2,5);
        stage.addActor(containerScore);

        //TODO 4 Inicializar Pausa y posicionarlo

        Image pause= new Image(AssetManager.pause);
        pause.setName("pause");
        pause.setWidth(Settings.PAUSE_WIDTH);
        pause.setHeight(Settings.PAUSE_HEIGHT);
        pause.setPosition((Settings.GAME_WIDTH)-pause.getWidth() - 2, 1);
        stage.addActor(pause);

        //TODO 5.1
        //TODO 5.3
        //  Inicializar highScore y prefs
        prefs=Gdx.app.getPreferences("prefs");
        highScore=prefs.getInteger("highScore",0);

        // Iniciem el GlyphLayout
        textLayout = new GlyphLayout();
        currentState = GameState.READY;

        // Assignem com a gestor d'entrada la classe InputHandler
        Gdx.input.setInputProcessor(new InputHandler(this));

    }

    private void drawElements() {

        // Recollim les propietats del Batch de l'Stage
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());

        // Pintem el fons de negre per evitar el "flickering"
        //Gdx.gl20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        //Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Inicialitzem el shaperenderer
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        // Definim el color (verd)
        shapeRenderer.setColor(new Color(0, 1, 0, 1));

        // Pintem la nau
        shapeRenderer.rect(spacecraft.getX(), spacecraft.getY(), spacecraft.getWidth(), spacecraft.getHeight());

        // Recollim tots els Asteroid
        ArrayList<Asteroid> asteroids = scrollHandler.getAsteroids();
        Asteroid asteroid;

        for (int i = 0; i < asteroids.size(); i++) {

            asteroid = asteroids.get(i);
            switch (i) {
                case 0:
                    shapeRenderer.setColor(1, 0, 0, 1);
                    break;
                case 1:
                    shapeRenderer.setColor(0, 0, 1, 1);
                    break;
                case 2:
                    shapeRenderer.setColor(1, 1, 0, 1);
                    break;
                default:
                    shapeRenderer.setColor(1, 1, 1, 1);
                    break;
            }
            shapeRenderer.circle(asteroid.getX() + asteroid.getWidth() / 2, asteroid.getY() + asteroid.getWidth() / 2, asteroid.getWidth() / 2);
        }

        //TODO 3.1 COIN draw
        //shapeRenderer.rect(coin.getX(), spacecraft.getY(), spacecraft.getWidth(), spacecraft.getHeight());
        //shapeRenderer.end();
    }

    //TODO 4 Mostrar Score y Pausa
    @Override
    public void show() {
        stage.getRoot().findActor("score").setVisible(true);
        stage.getRoot().findActor("pause").setVisible(true);
    }

    //TODO 4 render case PAUSE
    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Dibuixem tots els actors de l'stage
        stage.draw();

        // Depenent de l'estat del joc farem unes accions o unes altres
        switch (currentState) {
            case GAMEOVER:
                updateGameOver(delta);
                break;
            case RUNNING:
                updateRunning(delta);
                break;
            case READY:
                updateReady();
                break;
            case PAUSE:
                pause();
                updatePause();
                textLayout.setText(font,"Game Paused");
                break;
        }

        //drawElements();

    }

    //TODO 4 Metodo: updatePause
    private void updatePause(){
        batch.begin();
        font.draw(batch,textLayout,(Settings.GAME_WIDTH/2)-textLayout.width/2,(Settings.GAME_HEIGHT/2)-textLayout.height/2);
        batch.end();
    }

    //TODO 5.2 Mostrar mensaje en READY
    private void updateReady() {

        // Dibuixem el text al centre de la pantalla
        batch.begin();
        font.draw(batch, textLayout, (Settings.GAME_WIDTH / 2) - textLayout.width / 2, (Settings.GAME_HEIGHT / 2) - textLayout.height / 2);
        //stage.addActor(textLbl);
        textLayout.setText(font, "Are you ready?" + "\n\n" + "HighScore: " + highScore +"\n\n" + "Level : "+getLevelScore());
        batch.end();

    }

    private void updateRunning(float delta) {
        stage.act(delta);

        if (scrollHandler.collides(spacecraft)) {
            // Si hi ha hagut col·lisió: Reproduïm l'explosió i posem l'estat a GameOver
            //TODO 3.1 UpdateRunning - Tomar el tipo de objeto de colision: Si 'a'(Asteroid)->FIN de JUEGO | c(Coin)-> Actualizar Score moneda azul o amarilla
            char typeCollision= scrollHandler.getTypeCollision();
            switch (typeCollision){
                case ScrollHandler.ASTEROID:
                    AssetManager.explosionSound.play();
                    stage.getRoot().findActor("spacecraft").remove();
                    currentState = GameState.GAMEOVER;
                    break;
                case ScrollHandler.COIN:
                    if(scrollHandler.coin.colorCoin.equals("yellow")) {
                        updateScore(Settings.COIN_INCREASE_YELLOW);
                    }else if (scrollHandler.coin.colorCoin.equals("blue")){
                        updateScore(Settings.COIN_INCREASE_BLUE);
                    }
                    break;
            }

        }
    }

    private void updateGameOver(float delta) {
        stage.act(delta);

        batch.begin();
        font.draw(batch, textLayout, (Settings.GAME_WIDTH - textLayout.width) / 2, (Settings.GAME_HEIGHT - textLayout.height) / 2);
        // Si hi ha hagut col·lisió: Reproduïm l'explosió i posem l'estat a GameOver
        batch.draw((TextureRegion) AssetManager.explosionAnim.getKeyFrame(explosionTime, false), (spacecraft.getX() + spacecraft.getWidth() / 2) - 32, spacecraft.getY() + spacecraft.getHeight() / 2 - 32, 64, 64);
        batch.end();
        //TODO 5.1 Chequear si ha habido un highscore
        checkHighScore();
        //TODO 5.1 Mostrar Game Over, el score, y nivel que eres
        textLayout.setText(font, "Game Over :'(" + "\n\n" +  checkScore() +"\n"+ "Your score: " + score);
        explosionTime += delta;

    }

    //TODO 3.1 Metodo: Actualizar Score
    private void updateScore(int points){
        score=score+points;
        labelScore.setText("Score: " +String.valueOf(score));

        //TODO 3.2 Sonido bonus.
        AssetManager.bonus.play();
        //TODO 3.2 Escalado
        containerScore.addAction(Actions.sequence(
                Actions.scaleBy(0.2f, 0.2f, 0.2f),
                Actions.scaleBy(-0.2f, -0.2f, 0.2f)));
    }

    public void reset() {

        // Posem el text d'inici
        textLayout.setText(font, "Are you\nready?");
        // Cridem als restart dels elements.
        spacecraft.reset();
        scrollHandler.reset();

        // Posem l'estat a 'Ready'
        currentState = GameState.READY;

        // Afegim la nau a l'stage
        stage.addActor(spacecraft);

        // Posem a 0 les variables per controlar el temps jugat i l'animació de l'explosió
        explosionTime = 0.0f;

        //TODO 3.1 Reset score y label
        score=0;
        labelScore.setText("Score : "+String.valueOf(score));

    }


    @Override
    public void resize(int width, int height) {

    }

    //TODO 4 Metodo Pause
    @Override
    public void pause() {
        if (getCurrentState() == GameState.RUNNING){
            setCurrentState(GameState.PAUSE);
        }
    }

    @Override
    public void resume() {

    }

    //TODO 4 Ocultar Score y Pausa
    @Override
    public void hide() {
        stage.getRoot().findActor("score").setVisible(false);
        stage.getRoot().findActor("pause").setVisible(false);
    }

    @Override
    public void dispose() {

    }

    public Spacecraft getSpacecraft() {
        return spacecraft;
    }

    public Stage getStage() {
        return stage;
    }

    public ScrollHandler getScrollHandler() {
        return scrollHandler;
    }

    public GameState getCurrentState() {
        return currentState;
    }
    //TODO 4 Acciones de la pausa y running
    public void setCurrentState(GameState currentState) {

        switch (currentState){
            case RUNNING:
                AssetManager.music.setVolume(0.5f);
                show();
                break;
            case PAUSE:
                AssetManager.music.setVolume(0.02f);
                hide();
                break;
            case GAMEOVER:
                show();
                break;
        }
        this.currentState = currentState;
    }

    //TODO 5.1 Chequeo del Score para ver nivel en GAME OVER
    public String checkScore(){
        String strScore;
        if (score<100){
            strScore="You're a n00b!";
        }else if (score<150){
            strScore="Well done!";
        }else{
            strScore="Oh yeah!! You're a pro!";
        }
        return (strScore);
    }

    //TODO 5.1 5.3 Metodo Verificar si se ha obtenido un valor highScore mas alto
    public void checkHighScore(){
        if (highScore<score){
            prefs.putInteger("highScore", score);
            prefs.flush();
            highScore=score;
        }
    }

    //TODO 5.2 Metodo : Nivel de usuario High Score (READY)
    public String getLevelScore(){
        String level="";
        if (highScore<10){
            level="NOOb";
        }else if (highScore<150){
            level="Person";
        }else if (highScore>= 150){
            level="Pro";
        }
        return level;
    }
}
