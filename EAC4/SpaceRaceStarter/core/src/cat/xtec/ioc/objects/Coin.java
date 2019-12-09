package cat.xtec.ioc.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;
import com.sun.org.apache.xerces.internal.impl.dv.xs.YearDV;

import java.util.Random;

import cat.xtec.ioc.helpers.AssetManager;
import cat.xtec.ioc.utils.Methods;
import cat.xtec.ioc.utils.Settings;

//TODO 3.1 Class COIN
public class Coin extends Scrollable {

    private Circle collisionCircle;
    public String colorCoin;

    public Coin(float x, float y, float width, float height, float velocity) {
        super(x, y, width, height, velocity);

        // Creem el cercle
        collisionCircle = new Circle();

        //Definir si la moneda o Azul o Amarilla - Random del 1 al 10.( si sale <=9 sera amarilla y es >9 sera azul)
        setColorCoin();
        if (colorCoin.equals("blue")){
            this.velocity=Settings.COIN_SPEED_BLUE;
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        // Actualitzem el cercle de col·lisions (punt central de l'asteroid i el radi.
        collisionCircle.set(position.x + width / 2.0f, position.y + width / 2.0f, width / 2.0f);


    }

    @Override
    public void reset(float newX) {

        super.reset(newX);

        // La posició serà un valor aleatòri entre 0 i l'alçada de l'aplicació menys l'alçada
        position.x=Settings.GAME_WIDTH;
        position.y =  new Random().nextInt(Settings.GAME_HEIGHT - (int) height);
        setColorCoin();
        if (colorCoin.equals("blue")){
            this.velocity=Settings.COIN_SPEED_BLUE;
        }else{
            this.velocity=Settings.COIN_SPEED_YELLOW;
        }
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (colorCoin.equals("yellow")) {
            batch.draw(AssetManager.coinYellow, position.x, position.y, this.getOriginX(), this.getOriginY(), width, height, this.getScaleX(), this.getScaleY(), this.getRotation());
        }else {
            batch.draw(AssetManager.coinBlue, position.x, position.y, this.getOriginX(), this.getOriginY(), width, height, this.getScaleX(), this.getScaleY(), this.getRotation());
        }
    }

    // Retorna true si hi ha col·lisió
    public boolean collides(Spacecraft nau) {

        if (position.x <= nau.getX() + nau.getWidth()) {
            // Comprovem si han col·lisionat sempre i quan l'asteroid estigui a la mateixa alçada que la spacecraft
            boolean result=Intersector.overlaps(collisionCircle, nau.getCollisionRect());
            return  result;
        }
        return false;
    }

    //TODO 3.1 Metodo : Tipo de moneda - Atributo Valor de colorCoin
    private void setColorCoin(){
        int randomCoin=(int) (Math.random()*10)+1;
        if (randomCoin<=9){
            colorCoin="yellow";
        }else {
            colorCoin="blue";
        }
    }
}
