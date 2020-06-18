package nl.mahmood.localizationdemo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add(getResources().getString(R.string.spinner_item_1));
        spinnerArray.add(getResources().getString(R.string.spinner_item_2));
        spinnerArray.add(getResources().getString(R.string.spinner_item_3));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.spinner);
        sItems.setAdapter(adapter);

        String site = "site.tld";
        String days = "11";

        String notyet = getString(R.string.notyet, site, days);
        TextView textView2 = findViewById(R.id.textView2);
        textView2.setText(notyet);

        //get primaryLocale
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N)
        {
            Locale primaryLocale  = this.getResources().getConfiguration().getLocales().get(0);
        }
    }
}