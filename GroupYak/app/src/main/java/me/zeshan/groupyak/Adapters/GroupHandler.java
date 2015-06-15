package me.zeshan.groupyak.Adapters;

import android.app.Activity;
import android.content.Context;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.zeshan.groupyak.Databases.GroupDatabase;
import me.zeshan.groupyak.R;

public class GroupHandler {

    public static GroupArrayAdapter groupArrayAdapter;
    public static ListView groupList;

    public List<String> groups = new ArrayList<>();
    Context con;

    public GroupHandler(Context con) {
        this.con = con;
    }

    public void initialSetup() {
        groupArrayAdapter = new GroupArrayAdapter(con, R.layout.group_layout);
        groupList = (ListView) ((Activity)con).findViewById(R.id.groupList);

        GroupDatabase groupDatabase = new GroupDatabase(con);

        HashMap<String, String> map = groupDatabase.getGroups();
        for (String key: map.keySet()) {
            groups.add(map.get(key));
            groupArrayAdapter.add(new GroupText(key, map.get(key), false, false));
        }

        groupList.setAdapter(groupArrayAdapter);
    }

    public void tempAdd(String name, String ID) {
        groupArrayAdapter.add(new GroupText(name, ID, false, false));
        groupList.setAdapter(groupArrayAdapter);
    }
}
