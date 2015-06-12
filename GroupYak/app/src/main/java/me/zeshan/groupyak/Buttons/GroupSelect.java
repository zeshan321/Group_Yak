package me.zeshan.groupyak.Buttons;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import me.zeshan.groupyak.Adapters.GroupHandler;
import me.zeshan.groupyak.Adapters.GroupText;
import me.zeshan.groupyak.PostActivity;

public class GroupSelect {

    Context con;

    public GroupSelect(Context con) {
        this.con = con;

        setListener();
    }

    private void setListener() {
        ListView listView = GroupHandler.groupList;

        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                GroupText groupText = GroupHandler.groupArrayAdapter.getItem(position);

                Intent intent = new Intent(con, PostActivity.class);
                intent.putExtra("Display", groupText.groupName);
                intent.putExtra("ID", groupText.groupID);

                con.startActivity(intent);
            }
        });
    }
}
