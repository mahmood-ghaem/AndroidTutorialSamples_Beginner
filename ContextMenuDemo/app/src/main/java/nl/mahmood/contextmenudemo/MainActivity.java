package nl.mahmood.contextmenudemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    ListView listView;
    Button button;

    int listViewSelectedPosition;
    String[] stringArray;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 1 init listView, arrayAdapter, button
         */
        listView = findViewById(R.id.listView);
        stringArray = new String[]{"User 1", "User 2", "User 3"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stringArray);
        listView.setAdapter(arrayAdapter);

        button = findViewById(R.id.button);

        /**
         * 2 listener
         * call method for listViewLongClickListener
         * call method for buttonClickListener
         */
        listViewLongClickListener();
        buttonClickListener();
        /**
         * 3 register
         * context menu
         */
        registerForContextMenu(listView);
    }


    /**
     * 2 listener
     * method for listViewLongClickListener
     * method for buttonClickListener
     */
    private void listViewLongClickListener()
    {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
            {
                listViewSelectedPosition = position;
                return false;
            }
        });
    }
    private void buttonClickListener()
    {
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, button, Gravity.CENTER);
                popupMenu.getMenuInflater().inflate(R.menu.menu_option, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
                {
                    @Override
                    public boolean onMenuItemClick(MenuItem item)
                    {
                        Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

    /**
     * 4
     * onCreateContextMenu
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, v.getId(), 0, "Call");
        menu.add(0, v.getId(), 1, "SMS");

        menu.setHeaderTitle("your options");
    }

    /**
     * 5
     * onContextItemSelected
     */
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item)
    {
        switch (item.getOrder())
        {

            case 0:
                Toast.makeText(this, "Call " + stringArray[listViewSelectedPosition], Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(this, "SMS " + stringArray[listViewSelectedPosition], Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onContextItemSelected(item);

    }
}
