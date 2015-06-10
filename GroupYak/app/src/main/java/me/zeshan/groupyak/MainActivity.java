package me.zeshan.groupyak;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.HashMap;

import me.zeshan.groupyak.Adapters.GroupArrayAdapter;
import me.zeshan.groupyak.Adapters.GroupText;
import me.zeshan.groupyak.Database.GroupDatabase;


public class MainActivity extends ActionBarActivity {

    GroupArrayAdapter groupArrayAdapter;
    ListView groupList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        groupArrayAdapter = new GroupArrayAdapter(getApplicationContext(), R.layout.group_layout);
        groupList = (ListView) findViewById(R.id.groupList);

        GroupDatabase groupDatabase = new GroupDatabase(this);
        groupDatabase.addGroup("Testing", "ASD");

        HashMap<String, String> map = groupDatabase.getGroups();
        for (String s: map.keySet()) {
            groupArrayAdapter.add(new GroupText(s, map.get(s)));
        }

        groupList.setAdapter(groupArrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1, 1, 1, "Settings");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
