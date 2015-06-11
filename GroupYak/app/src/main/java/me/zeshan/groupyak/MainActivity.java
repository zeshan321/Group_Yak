package me.zeshan.groupyak;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import me.zeshan.groupyak.Adapters.GroupArrayAdapter;
import me.zeshan.groupyak.Adapters.GroupHandler;
import me.zeshan.groupyak.Buttons.FloatListeners;
import me.zeshan.groupyak.Buttons.GroupListLong;


public class MainActivity extends ActionBarActivity {

    GroupArrayAdapter groupArrayAdapter;
    ListView groupList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        groupArrayAdapter = new GroupArrayAdapter(getApplicationContext(), R.layout.group_layout);
        groupList = (ListView) findViewById(R.id.groupList);

        // Setup groups stored in DB
        new GroupHandler(this).initialSetup();

        // Setup button listeners
        new FloatListeners(this);
        new GroupListLong(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1, 1, 1, "Settings");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
