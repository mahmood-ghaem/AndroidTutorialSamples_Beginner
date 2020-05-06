package nl.mahmood.customtextview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewGroup container = findViewById(R.id.container);
        MyTextView tv = new MyTextView(this, "Purple", android.R.color.holo_purple);
        container.addView(tv);
        tv = new MyTextView(this, "Orange", android.R.color.holo_orange_light);
        container.addView(tv);
        tv = new MyTextView(this, "Red", android.R.color.holo_red_light);
        container.addView(tv);
        tv = new MyTextView(this, "Green", android.R.color.holo_green_light);
        container.addView(tv);
        tv = new MyTextView(this, "Blue", android.R.color.holo_blue_light);
        container.addView(tv);
    }
}
