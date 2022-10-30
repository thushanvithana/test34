package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity6 extends AppCompatActivity {

    ImageView imageViewPlay, imageViewPause, imageViewStop;
    MediaPlayer mediaPlayer;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_main6);

        button = findViewById(R.id.button25);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(i3);
            }
        });

        imageViewPlay = findViewById(R.id.m_play);
        imageViewPause = findViewById(R.id.m_pause);
        imageViewStop = findViewById(R.id.m_stop);

        imageViewPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play();
            }
        });

        imageViewPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pause();
            }

        });

        imageViewStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stop();
            }
        });
    }

    private void stop() {
        if (mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void play() {
        if (mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(this, R.raw.song);

        }

        mediaPlayer.start();
    }

    private void pause() {
        if (mediaPlayer != null){
            mediaPlayer.pause();
        }
    }
}