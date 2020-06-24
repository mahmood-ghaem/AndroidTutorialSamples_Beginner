package nl.mahmood.androidforegroundservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
{
    private Intent serviceIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serviceIntent = new Intent(this, MyService.class);
    }
    public void startService(View view)
    {
        ContextCompat.startForegroundService(MainActivity.this, serviceIntent);
    }

    public void nextActivity(View view)
    {
        startActivity(new Intent(MainActivity.this, MainActivity2.class));
    }

    public void stopService(View view)
    {
        stopService(serviceIntent);
    }
}