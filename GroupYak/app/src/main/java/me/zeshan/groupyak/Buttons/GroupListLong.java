package me.zeshan.groupyak.Buttons;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBar;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import me.zeshan.groupyak.Adapters.GroupHandler;
import me.zeshan.groupyak.Adapters.GroupText;
import me.zeshan.groupyak.Databases.GroupDatabase;
import me.zeshan.groupyak.R;

public class GroupListLong {

    Context con;
    ActionMode actionMode;
    ActionBar actionBar;

    boolean isOpen = false;
    List<String> selected = new ArrayList<>();

    public GroupListLong(Context con, ActionBar actionBar) {
        this.con = con;
        this.actionBar = actionBar;

        onHold();
    }

    private void onHold() {
        GroupHandler.groupList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                ImageView imageView = (ImageView) view.findViewById(R.id.groupImage);
                ImageView imageView1 = (ImageView) view.findViewById(R.id.check_icon);
                GroupText groupText = GroupHandler.groupArrayAdapter.getItem(position);

                if (!groupText.selected) {
                    imageView.setVisibility(View.GONE);
                    imageView1.setVisibility(View.VISIBLE);

                    selected.add(String.valueOf(position));
                    groupText.selected = true;
                } else {
                    imageView.setVisibility(View.VISIBLE);
                    imageView1.setVisibility(View.GONE);

                    selected.remove(String.valueOf(position));
                    groupText.selected = false;
                }

                if (selected.size() > 0 && !isOpen) {
                    isOpen = true;

                    actionMode = ((Activity) con).startActionMode(mActionModeCallback);
                } else {
                    if (selected.size() == 0 && actionMode != null) {
                        isOpen = false;

                        actionMode.finish();

                    }
                }
                return true;
            }
        });
    }

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.delete_menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.delete:
                    if (!selected.isEmpty()) {
                        GroupDatabase groupDatabase = new GroupDatabase(con);
                        while (selected.listIterator().hasNext()) {
                            String pos = selected.listIterator().next();

                            // Remove from DB
                            groupDatabase.deleteGroup(GroupHandler.groupArrayAdapter.getItem(Integer.parseInt(pos)).groupID);

                            GroupHandler.groupArrayAdapter.removeGroup(Integer.parseInt(pos));
                            selected.remove(pos);
                        }

                        if (selected.isEmpty()) {
                            actionMode.finish();
                            isOpen = false;
                        }

                        GroupHandler.groupArrayAdapter.notifyDataSetChanged();
                    }
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            isOpen = false;

            actionMode.finish();

            while (selected.listIterator().hasNext()) {
                String pos = selected.listIterator().next();

                GroupText groupText = GroupHandler.groupArrayAdapter.getItem(Integer.parseInt(pos));
                groupText.selected = false;

                selected.remove(pos);
            }

            GroupHandler.groupArrayAdapter.notifyDataSetChanged();
        }
    };
}
