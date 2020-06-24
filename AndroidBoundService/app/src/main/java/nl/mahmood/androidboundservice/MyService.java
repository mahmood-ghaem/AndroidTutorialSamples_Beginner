package nl.mahmood.androidboundservice;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import java.util.Random;


public class MyService extends Service
{

    private String TAG = "@@@@@ MyService";

    // Binder given to clients (notice class declaration below)
    private final IBinder mBinder = new MyBinder();
    // Random number generator
    private final Random mGenerator = new Random();

    /**
     * For Android versions 26 and above, this is how you need to start a service if you want it to run indefinitely.
     * If you want to make service as foreground bound service you can override onCreate method and do that.
     */
//    @Override
//    public void onCreate()
//    {
//        super.onCreate();
//        if (Build.VERSION.SDK_INT >= 26)
//        {
//            String CHANNEL_ID = "my_channel_01";
//            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
//                    "My Channel",
//                    NotificationManager.IMPORTANCE_DEFAULT);
//
//            ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(channel);
//
//            Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
//                    .setContentTitle("A service is running.")
//                    .setContentText("").build();
//
//            startForeground(1, notification);
//        }
//    }


    /**
     * Class used for the client Binder. The Binder object is responsible for returning an instance
     * of "MyService" to the client.
     */
    public class MyBinder extends Binder
    {
        MyService getService()
        {
            // Return this instance of MyService so clients can call public methods
            return MyService.this;
        }
    }

    /**
     * This is how the client gets the IBinder object from the service. It's retrieve by the "ServiceConnection"
     * which you'll see later.
     **/
    @Override
    public IBinder onBind(Intent intent)
    {
        Log.d(TAG, "onBind");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent)
    {
        Log.d(TAG, "onUnbind");

        return false;
    }

    /**
     * method for clients to get a random number from 0 - 100
     */
    public int getRandomNumber()
    {
        return mGenerator.nextInt(100);
    }
}
