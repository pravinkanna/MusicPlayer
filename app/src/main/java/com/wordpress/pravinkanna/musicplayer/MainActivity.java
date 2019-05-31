package com.wordpress.pravinkanna.musicplayer;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Button playBtn, pauseBtn, stopBtn;
    SeekBar volumeSb, progressSb;
    MediaPlayer mediaPlayer;
    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playBtn = findViewById(R.id.button_play);
        pauseBtn = findViewById(R.id.button_pause);
        stopBtn = findViewById(R.id.button_stop);
        volumeSb = findViewById(R.id.seekBar_volume);
        progressSb = findViewById(R.id.seekBar_timeline);

        mediaPlayer = MediaPlayer.create(this, R.raw.song);
        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        volumeSb.setMax(maxVolume);
        volumeSb.setProgress(currentVolume);
        volumeSb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        



        progressSb.setMax(mediaPlayer.getDuration());
        progressSb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                progressSb.setProgress(mediaPlayer.getCurrentPosition());
            }
        }, 0, 500);


    }

    public void playMusic(View view){
        mediaPlayer.start();
    }

    public void pauseMusic(View view){
        mediaPlayer.pause();
    }

    public void stopMusic(View view){
        mediaPlayer.stop();
    }
}
