package com.developer.sounddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Getting start Step by Step:
 * 1 - add a music file in this case cowboy.mp3 in raw folder
 * 2 - create a media player
 * 3 - add play and pause button(set on click and name) in layout then write click methods
 * 4 - add two seek bar in layout and set the name for them
 * 5 - create volumeControl object and set setOnSeekBarChangeListener method
 *     5-3 create audioManager
 *     5-4 setStreamVolume
 *     5-5 create maxVolume and currentVolume
 *     5-6 setMax and setProgress
 * 6 - find scrubSeekBar and set that
 * 7 - create handler and runnable
 */

public class MainActivity extends AppCompatActivity
{
    /**
     * step 2-1
     */
    MediaPlayer mediaPlayer;
    /**
     * step 5-3-1
     */
    AudioManager audioManager;
    TextView textView;
    /**
     * step 7-1
     */
    final Handler handler = new Handler();
    Runnable runnable;

    /**
     * step 3-1
     */
    public void play(View view)
    {
        mediaPlayer.start();
        /**
         * step 7-3
         */
        handler.postDelayed(runnable,1000);
        textView.setText("Sound is playing");
    }
    /**
     * step 3-2
     */
    public void pause(View view)
    {
        mediaPlayer.pause();
        textView.setText("Sound is pause");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView3);
        /**
         * step 5-3-2
         */
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        /**
         * step 5-5
         */
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        /**
         * step 2-2
         */
        mediaPlayer = MediaPlayer.create(this, R.raw.cowboy);
        /**
         *  step 5-1
         */
        SeekBar volumeControl = findViewById(R.id.volumeSeekBar);
        /**
         * step 5-6
         */
        volumeControl.setMax(maxVolume);
        volumeControl.setProgress(currentVolume);
        /**
         * step 5-2
         */
        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                /**
                 * step 5-4
                 */
                Toast.makeText(MainActivity.this, "Seekbar changed", Toast.LENGTH_SHORT).show();
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });

        /**
         * step 6-1
         */
        final SeekBar scrubSeekBar = findViewById(R.id.scrubSeekBar);
        /**
         * step 6-2
         */
        scrubSeekBar.setMax(mediaPlayer.getDuration());
        /**
         * step 6-3
         */
        scrubSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                /**
                 * step 6-4
                 */
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        /**
         * step 7-2
         */
        runnable = new Runnable()
        {
            @Override
            public void run()
            {

                scrubSeekBar.setProgress(mediaPlayer.getCurrentPosition());

                handler.postDelayed(this,1000);
            }


        };
    }
}
