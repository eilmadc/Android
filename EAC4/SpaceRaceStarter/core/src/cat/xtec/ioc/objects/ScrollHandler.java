package cat.xtec.ioc.objects;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import java.util.ArrayList;
import java.util.Random;

import cat.xtec.ioc.screens.GameScreen;
import cat.xtec.ioc.utils.Methods;
import cat.xtec.ioc.utils.Settings;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.addAction;

public class ScrollHandler extends Group {

    // Fons de pantalla
    Background bg, bg_back;

    // Asteroides
    int numAsteroids;
    private ArrayList<Asteroid> asteroids;

    //TODO 3.1 COIN y tipo de objeto de colision
    public Coin coin;
    private char typeObjectCollision;
    public static final char ASTEROID='a';
    public static final char COIN='c';

    // Objecte Random
    Random r;


    public ScrollHandler() {

        // Creem els dos fons
        bg = new Background(0, 0, Settings.GAME_WIDTH * 2, Settings.GAME_HEIGHT, Settings.BG_SPEED);
        bg_back = new Background(bg.getTailX(), 0, Settings.GAME_WIDTH * 2, Settings.GAME_HEIGHT, Settings.BG_SPEED);

        // Afegim els fons al grup
        addActor(bg);
        addActor(bg_back);

        // Creem l'objecte random
        r = new Random();

        // Comencem amb 3 asteroids
       numAsteroids = 3;

        // Creem l'ArrayList
        asteroids = new ArrayList<Asteroid>();

        // Definim una mida aleatòria entre el mínim i el màxim
        float newSize = Methods.randomFloat(Settings.MIN_ASTEROID, Settings.MAX_ASTEROID) * 34;

        // Afegim el primer Asteroid a l'Array i al grup
        Asteroid asteroid = new Asteroid(Settings.GAME_WIDTH, r.nextInt(Settings.GAME_HEIGHT - (int) newSize), newSize, newSize, Settings.ASTEROID_SPEED);
        asteroids.add(asteroid);
        addActor(asteroid);

        // Des del segon fins l'últim asteroide
        for (int i = 1; i < numAsteroids; i++) {
            // Creem la mida al·leatòria
            newSize = Methods.randomFloat(Settings.MIN_ASTEROID, Settings.MAX_ASTEROID) * 34;
            // Afegim l'asteroid.
            asteroid = new Asteroid(asteroids.get(asteroids.size() - 1).getTailX() + Settings.ASTEROID_GAP, r.nextInt(Settings.GAME_HEIGHT - (int) newSize), newSize, newSize, Settings.ASTEROID_SPEED);
            // Afegim l'asteroide a l'ArrayList
            asteroids.add(asteroid);
            // Afegim l'asteroide al grup d'actors
            addActor(asteroid);
        }

        //TODO 3.1 COIN Actor
        coin=new Coin(Settings.GAME_WIDTH,r.nextInt(Settings.GAME_HEIGHT - (int) Settings.COIN_SIZE),Settings.COIN_SIZE,Settings.COIN_SIZE,Settings.COIN_SPEED_YELLOW );
        addActor(coin);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        // Si algun element està fora de la pantalla, fem un reset de l'element.
        if (bg.isLeftOfScreen()) {
            bg.reset(bg_back.getTailX());

        } else if (bg_back.isLeftOfScreen()) {
            bg_back.reset(bg.getTailX());

        }

        for (int i = 0; i < asteroids.size(); i++) {

            Asteroid asteroid = asteroids.get(i);
            if (asteroid.isLeftOfScreen()) {
                if (i == 0) {
                    asteroid.reset(asteroids.get(asteroids.size() - 1).getTailX() + Settings.ASTEROID_GAP);
                } else {
                    asteroid.reset(asteroids.get(i - 1).getTailX() + Settings.ASTEROID_GAP);
                }
            }
        }

        //TODO 3.1 action Coin
        if (coin.isLeftOfScreen()) {
            coin.reset(coin.getTailX());
        }
    }
// TODO 3.1 Verificar tipo de colision
    public boolean collides(Spacecraft nau) {

        // Comprovem les col·lisions entre cada asteroid i la nau
        for (Asteroid asteroid : asteroids) {
            if (asteroid.collides(nau)) {
                typeObjectCollision=ASTEROID;
                return true;
            }
        }

        if (coin.collides(nau)){
            typeObjectCollision=COIN;
            coin.reset(Settings.GAME_WIDTH);
            return true;
        }
        return false;
    }

    public void reset() {

        // Posem el primer asteroid fora de la pantalla per la dreta
        asteroids.get(0).reset(Settings.GAME_WIDTH);
        // Calculem les noves posicions de la resta d'asteroids.
        for (int i = 1; i < asteroids.size(); i++) {

            asteroids.get(i).reset(asteroids.get(i - 1).getTailX() + Settings.ASTEROID_GAP);

        }
    }

    public ArrayList<Asteroid> getAsteroids() {
        return asteroids;
    }

    //TODO 3.1 Devolver el tipo de objeto colisionado a GameScreen ( Actualizar Score o Fin de Juego)
    public char getTypeCollision(){
        return typeObjectCollision;
    }
}