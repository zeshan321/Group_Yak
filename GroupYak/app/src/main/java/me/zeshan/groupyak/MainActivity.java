package me.zeshan.groupyak;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import me.zeshan.groupyak.Adapters.GroupArrayAdapter;
import me.zeshan.groupyak.Adapters.GroupHandler;
import me.zeshan.groupyak.Buttons.FloatListeners;
import me.zeshan.groupyak.Buttons.GroupListLong;
import me.zeshan.groupyak.Util.KitKatUI;


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

        // KitKat UI
        new KitKatUI(this);
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Profile Options");
        menu.add(0, 1000, 0, "Header1"); // give your menus distinct ids!!!
        menu.add(0, 1001, 0, "Header2");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1000:
                //first ContextMenu option I picked this to start the  new activity

                break;
            case 1001:
                //stuff for option 2 of the ContextMenu
                break;
        }
        return super.onContextItemSelected(item);
    }
}
