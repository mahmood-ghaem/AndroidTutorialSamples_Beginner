package nl.mahmood.sharedpreferencesdemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * We learn about SharedPreferences in this sample.
 * and try with WebView and HTML text inside that.
 */

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText = findViewById(R.id.editText);
        Button button = findViewById(R.id.button);
        WebView webView = findViewById(R.id.webView);
        final TextView textView = findViewById(R.id.textView);

        webView.setVerticalScrollBarEnabled(false);
        webView.loadData(getString(R.string.content), "text/html; charset=utf-8", "utf-8");

        final SharedPreferences sharedPreferences = this.getSharedPreferences
                ("nl.mahmood.sharedpreferencesdemo", Context.MODE_PRIVATE);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sharedPreferences.edit().putString("UserName", "You are " + editText.getText().toString()).apply();
                textView.setText("Welcome "+editText.getText().toString()+" your name saved. I will know you next time.");
            }
        });
        String string = sharedPreferences.getString("UserName", "I don't know who you are?");
        textView.setText(string);

    }
}
