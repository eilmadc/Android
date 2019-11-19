package com.example.eac3_p3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.io.IOException;

import static android.media.MediaPlayer.create;

public class MainActivity extends AppCompatActivity {

    private AnimationDrawable simpsonsAnimation;
    Animation rotateAnimation;
    Animation antirotateAnimation;
    Animation rotateullAnimation;
    Animation translateAnimation;
    private ImageView simpsons;
    private ImageView homerdonut;
    private ImageView homerull;
    private ImageView homereng1;
    private ImageView homereng2;
    private ImageView homereng3;
    boolean running;
    MediaPlayer mplayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TITLE SIMPSONS
        simpsons = (ImageView) findViewById(R.id.ivsimpsons1);
        simpsons.setBackgroundResource(R.drawable.simpsons_anim);
        simpsonsAnimation = (AnimationDrawable) simpsons.getBackground();
        simpsonsAnimation.start();

        running = false;

        //MEDIA PLAYER ASIGN RESOURCE mp3
        final MediaPlayer mplayer=MediaPlayer.create(this, R.raw.the_simpsons);

        //ASIGN BUTTONS HOMER
        homerdonut = (ImageView) findViewById(R.id.ivdonut);
        homerull = (ImageView) findViewById(R.id.ivull);
        homereng1 = (ImageView) findViewById(R.id.ivengvermell);
        homereng2 = (ImageView) findViewById(R.id.ivengverd);
        homereng3 = (ImageView) findViewById(R.id.ivengblau);

        //CLICK LISTENER TITLE
        simpsons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!running) {
                    //ASSIGN IMAGES IN BUTTON onTouch
                    homerdonut.setBackgroundResource(R.drawable.donut);
                    homerull.setBackgroundResource(R.drawable.ull);
                    homereng1.setBackgroundResource(R.drawable.engranatge_vermell);
                    homereng2.setBackgroundResource(R.drawable.engranatge_verd);
                    homereng3.setBackgroundResource(R.drawable.engranatge_blau);

                    //BEGIN ANIMATIONS
                    rotateAnimation();
                    translateAnimation();

                    //CLICK LISTENER DONUT FOR AUDIO (Play/Pause)
                    homerdonut.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (!mplayer.isPlaying()) {
                                mplayer.start();
                            } else {
                                mplayer.pause();
                            }
                        }
                    });

                    //CONTROL CLICK
                    running = true;
                } else {
                    //ON RECLICK PUT Images view TO NULL
                    homerdonut.setBackgroundResource(0);
                    homerull.setBackgroundResource(0);
                    homereng1.setBackgroundResource(0);
                    homereng2.setBackgroundResource(0);
                    homereng3.setBackgroundResource(0);
                    running = false;
                }
            }
        });
    }

    //METODO PARA INTEGRAR LAS ROTACIONES MEDIANTE LAS ANIMACIONES
    //EN SENTIDO DEL RELOJ Y AL REVES, SEGUN CORRESPONDA
    private void rotateAnimation() {

        rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_clock);
        antirotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_anti_clock);
        rotateullAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_ull);

        homereng1.startAnimation(antirotateAnimation);
        homereng2.startAnimation(rotateAnimation);
        homereng3.startAnimation(antirotateAnimation);
        homerull.startAnimation(rotateullAnimation);
    }

    //METODO PARA INTEGRAR EL MOVIMIENTO Y ROTACION
    private void translateAnimation() {

        translateAnimation = AnimationUtils.loadAnimation(this, R.anim.translate_and_rotate);

        homerdonut.startAnimation(translateAnimation);
    }
}
