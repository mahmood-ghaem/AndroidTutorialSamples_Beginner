package nl.mahmood.androidboundservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//https://developer.android.com/guide/components/bound-services
//https://codingwithmitch.com/blog/bound-services-on-android/
public class MainActivity extends AppCompatActivity
{

    MyService mService;
    Boolean mIsBound;
    private String TAG = "@@@@@ MainActivity";
    Button button;
    TextView textViewRandomNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        textViewRandomNumber = findViewById(R.id.textViewRandomNumber);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        startService();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        if (mIsBound)
        {
            unbindService(serviceConnection);
            mIsBound = false;
        }
    }

    private void startService()
    {
        Intent serviceIntent = new Intent(this, MyService.class);
        startService(serviceIntent);

        bindService();
    }

    private void bindService()
    {
        Intent serviceBindIntent = new Intent(this, MyService.class);
        bindService(serviceBindIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection serviceConnection = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName className, IBinder iBinder)
        {
            Log.d(TAG, "ServiceConnection: connected to service.");
            // We've bound to MyService, cast the IBinder and get MyBinder instance
            MyService.MyBinder binder = (MyService.MyBinder) iBinder;
            mService = binder.getService();
            mIsBound = true;
            button.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    textViewRandomNumber.setText("Random number from service: " + mService.getRandomNumber());

                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0)
        {
            Log.d(TAG, "ServiceConnection: disconnected from service.");
            mIsBound = false;
        }
    };
}