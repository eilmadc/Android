package cat.xtec.ioc.utils;

public class Settings {

    // Mida del joc, s'escalarà segons la necessitat
    public static final int GAME_WIDTH = 240;
    public static final int GAME_HEIGHT = 120;

    // Propietats de la nau
    public static final float SPACECRAFT_VELOCITY = 50;
    public static final int SPACECRAFT_WIDTH = 32;
    public static final int SPACECRAFT_HEIGHT = 32;
    public static final float SPACECRAFT_STARTX = 20;
    public static final float SPACECRAFT_STARTY = GAME_HEIGHT/2 - SPACECRAFT_HEIGHT/2;

    // Rang de valors per canviar la mida de l'asteroide.
    public static final float MAX_ASTEROID = 1.5f;
    public static final float MIN_ASTEROID = 0.5f;

    // Configuració Scrollable
    public static final int ASTEROID_SPEED = -150;
    public static final int ASTEROID_GAP = 75;
    public static final int BG_SPEED = -100;

    // TODO Exercici 2: Propietats per la moneda
    public static final int COIN_SIZE = 16;
    public static final int COIN_INCREASE_YELLOW = 10; // s'incrementa en 100 cada cop que toca una moneda
    public static final int COIN_INCREASE_BLUE = 50;
    public static final int COIN_SPEED_YELLOW = -175;
    public static final int COIN_SPEED_BLUE = -250;

    //TODO 4 Propiedades pausa
    public static final int PAUSE_WIDTH=16;
    public static final int PAUSE_HEIGHT=16;



}
