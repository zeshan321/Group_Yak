package me.zeshan.groupyak;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import me.zeshan.groupyak.Adapters.GroupHandler;
import me.zeshan.groupyak.Buttons.FloatListeners;
import me.zeshan.groupyak.Buttons.GroupListLong;
import me.zeshan.groupyak.Buttons.GroupSelect;
import me.zeshan.groupyak.Util.GroupsUpdater;
import me.zeshan.groupyak.Util.KitKatUI;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup groups stored in DB and check for updates
        GroupHandler groupHandler = new GroupHandler(this);
        groupHandler.initialSetup();
        new GroupsUpdater(this, groupHandler.groups);

        // Setup button listeners
        new FloatListeners(this);
        new GroupListLong(this, getSupportActionBar());
        new GroupSelect(this);

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
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.delete_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                // your first action code
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
