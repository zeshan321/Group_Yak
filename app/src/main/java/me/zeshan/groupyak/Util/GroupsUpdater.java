package me.zeshan.groupyak.Util;

import android.content.Context;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import me.zeshan.groupyak.Adapters.GroupHandler;
import me.zeshan.groupyak.Adapters.GroupText;
import me.zeshan.groupyak.Databases.GroupDatabase;
import me.zeshan.groupyak.SettingsManager;

public class GroupsUpdater {

    Context con;
    List<String> groups;

    public GroupsUpdater(Context con, List groups) {
        this.con = con;
        this.groups = groups;

        checkUpdates();
    }

    private void checkUpdates() {
        final GroupDatabase groupDatabase = new GroupDatabase(con);
        final ParseQuery<ParseObject> query = ParseQuery.getQuery("Groups");

        query.whereContainedIn("ID", groups);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {
                if (e == null) {
                    for (ParseObject parseObject : parseObjects) {
                        if (parseObject != null) {
                            String display = parseObject.getString("Display");
                            String ID = parseObject.getString("ID");
                            String owner = parseObject.getString("Owner");

                            // Update DB
                            groupDatabase.updateGroup(display, ID);

                            // Update GroupText
                            GroupText groupText = GroupHandler.groupArrayAdapter.getItem(GroupHandler.groupArrayAdapter.getByID(ID));
                            groupText.groupName = display;
                            if (owner.equals(new SettingsManager(con).getString("ID"))) {
                                groupText.isOwner = true;
                            }
                        }
                    }

                    // Update ListView
                    GroupHandler.groupArrayAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
