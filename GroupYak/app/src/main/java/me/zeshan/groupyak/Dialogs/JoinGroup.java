package me.zeshan.groupyak.Dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import me.zeshan.groupyak.Adapters.GroupHandler;
import me.zeshan.groupyak.Databases.GroupDatabase;
import me.zeshan.groupyak.R;
import me.zeshan.groupyak.Util.ToastMessage;

public class JoinGroup {

    Context con;

    public JoinGroup(Context con) {
        this.con = con;

        createDialog();
    }

    private void createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(con);
        LayoutInflater inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View v = inflater.inflate(R.layout.dialog_joingroup, null);

        builder.setView(v).setPositiveButton("Join", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                EditText editText = (EditText) v.findViewById(R.id.JoinName);

                final GroupDatabase groupDatabase = new GroupDatabase(con);
                final String name = editText.getText().toString();

                if (groupDatabase.containsGroup(name)) {
                    new ToastMessage(con, con.getString(R.string.already_in_group), 0).sendToast();
                    return;
                }

                new ToastMessage(con, con.getString(R.string.loading), 0).sendToast();

                final ParseQuery<ParseObject> query = ParseQuery.getQuery("Groups");
                query.whereEqualTo("ID", name);
                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {
                        if (e == null) {
                            if (parseObjects.size() > 0) {
                                String display = null;
                                for (ParseObject groups : parseObjects) {
                                    if (groups != null) {
                                        display = groups.getString("Display");
                                    }
                                }

                                groupDatabase.addGroup(display, name);
                                new GroupHandler(con).tempAdd(display, name);

                                new ToastMessage(con, con.getString(R.string.group_added), 0).sendToast();
                            } else {
                                new ToastMessage(con, con.getString(R.string.group_not_found), 0).sendToast();
                            }
                        } else {
                            new ToastMessage(con, con.getString(R.string.error), 0).sendToast();
                        }
                    }
                });
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

        builder.create();
        builder.show();
    }
}
