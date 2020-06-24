package nl.mahmood.custombroadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void broadcastIntent(View view)
    {
        sendBroadcast(new Intent(this, MyReceiver.class).setAction("MyAction"));


    }
}