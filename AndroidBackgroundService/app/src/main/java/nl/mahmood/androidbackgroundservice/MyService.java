package nl.mahmood.androidbackgroundservice;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyService extends Service
{

    private MediaPlayer soundPlayer;

    @Override
    public void onCreate()
    {
        Toast.makeText(this, "Service Created", Toast.LENGTH_SHORT).show();
        soundPlayer = MediaPlayer.create(this, R.raw.song);
        soundPlayer.setLooping(false);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {

        Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();
        soundPlayer.start();
        return START_STICKY;

    }

    @Override
    public void onDestroy()
    {

        Toast.makeText(this, "Service Stopped", Toast.LENGTH_SHORT).show();
        soundPlayer.stop();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }
}
