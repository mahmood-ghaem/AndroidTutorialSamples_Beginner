package nl.mahmood.multiplicationtables;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

/**
 * 1 - add listView and seekBar to layout and set id
 * 2 - as usual find widget
 * 3 - setProgress and setMax for seekBar
 * 4 - setOnSeekBarChangeListener for seekBar
 * 5 - make arrayList and set value for that
 * 6 - make arrayAdapter and set for listView
 */

public class MainActivity extends AppCompatActivity
{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * step 2
         */
        SeekBar seekBar = findViewById(R.id.seekBar);
        final ListView listView = findViewById(R.id.listView);
        /**
         * step 3
         */
        seekBar.setMax(20);
        seekBar.setProgress(10);
        /**
         * step 4
         */
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                /**
                 * step 5-1
                 */
                ArrayList<String> arrayList = new ArrayList<String>();
                /**
                 * step 5-2
                 */
                for (int i = 1; i <= 10; i++) {
                    arrayList.add(i + " * " + progress + " = " + Integer.toString(i * progress));
                }
                /**
                 * step 6-1
                 */
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);
                /**
                 * step 6-2
                 */
                listView.setAdapter(arrayAdapter);
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
    }
}
