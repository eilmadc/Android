package cat.xtec.ioc.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class AssetManager {

    // Sprite Sheet
    public static Texture sheet;

    // Nau i fons
    public static TextureRegion spacecraft, spacecraftDown, spacecraftUp, background;

    // Asteroid
    public static TextureRegion[] asteroid;
    public static Animation asteroidAnim;

    // Explosió
    public static TextureRegion[] explosion;
    public static Animation explosionAnim;

    //TODO 3.1 COIN Texture Region
    public static TextureRegion coinYellow;
    public static TextureRegion coinBlue;
    public static Animation coinYellowAnimation;
    public static Animation coinBlueAnimation;

    //TODO 4 PAUSE Texture Region
    public static TextureRegion pause;

    // Sons
    public static Sound explosionSound;
    public static Music music;
    public static Sound bonus;


    // Font
    public static BitmapFont font;



    public static void load() {
        // Carreguem les textures i li apliquem el mètode d'escalat 'nearest'
        sheet = new Texture(Gdx.files.internal("sheet.png"));
        sheet.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        // Sprites de la nau
        spacecraft = new TextureRegion(sheet, 0, 0, 32,32 );
        spacecraft.flip(false, true);

        spacecraftUp = new TextureRegion(sheet, 32, 0, 32, 32);
        spacecraftUp.flip(false, true);

        spacecraftDown = new TextureRegion(sheet, 64, 0, 32, 32);
        spacecraftDown.flip(false, true);



        // Carreguem els 16 estats de l'asteroid
        asteroid = new TextureRegion[16];
        for (int i = 0; i < asteroid.length; i++) {

            asteroid[i] = new TextureRegion(sheet, i * 32, 32, 32, 32);
          //  asteroid[i].flip(false, true);

        }



        // Creem l'animació de l'asteroid i fem que s'executi contínuament en sentit anti-horari
        asteroidAnim = new Animation(0.05f, asteroid);
        asteroidAnim.setPlayMode(Animation.PlayMode.LOOP);

        // Creem els 16 estats de l'explosió
        explosion = new TextureRegion[16];

        // Carreguem els 16 estats de l'explosió
        int index = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                explosion[index++] = new TextureRegion(sheet, j * 64,  i * 64 + 64, 64, 64);
                explosion[index-1].flip(false, true);
            }
        }

        // Finalment creem l'animació
        explosionAnim = new Animation(0.08f, explosion);//0.04f

        // Fons de pantalla
        background = new TextureRegion(sheet, 0, 192, 480, 120);
    //    background.flip(false, true);

        //TODO 3.1 Regions Coin Blue and Yellow
        coinYellow=new TextureRegion(sheet,158,0,22,24);
        coinBlue=new TextureRegion(sheet,180,0,22,24);
        coinYellowAnimation = new Animation(0.05f, coinYellow);
        coinYellowAnimation.setPlayMode(Animation.PlayMode.LOOP_REVERSED);
        coinBlueAnimation = new Animation(0.05f, coinBlue);
        coinBlueAnimation.setPlayMode(Animation.PlayMode.LOOP_REVERSED);


        //TODO 4 Region Pausa
        pause=new TextureRegion(sheet,126,0,31,32);
        pause.flip(false,true);


        /******************************* Sounds *************************************/
        // Explosió
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.wav"));

        // Música del joc
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/Ireland.ogg"));
        music.setVolume(0.15f);
        music.setLooping(true);

        //TODO 3.2 Musica Bonus
        bonus=Gdx.audio.newSound(Gdx.files.internal("sounds/coin.wav"));


        /******************************* Text *************************************/
        // Font space
        FileHandle fontFile = Gdx.files.internal("fonts/space.fnt");
        font = new BitmapFont(fontFile, true);
        font.getData().setScale(0.3f);
        font.setColor(Color.GRAY);


    }



    public static void dispose() {

        // Alliberem els recursos gràfics i de audio
        sheet.dispose();
        explosionSound.dispose();
        music.dispose();
        bonus.dispose();

    }
}
