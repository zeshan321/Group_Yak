package me.zeshan.groupyak.Dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import me.zeshan.groupyak.Adapters.GroupHandler;
import me.zeshan.groupyak.Databases.GroupDatabase;
import me.zeshan.groupyak.R;
import me.zeshan.groupyak.SettingsManager;
import me.zeshan.groupyak.Util.StringUtil;
import me.zeshan.groupyak.Util.ToastMessage;

public class CreateGroup {

    Context con;

    public CreateGroup(Context con) {
        this.con = con;

        createDialog();
    }

    private void createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(con);
        LayoutInflater inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View v = inflater.inflate(R.layout.dialog_creategroup, null);

        // EdiText listener
        new CreateListener(con, v);

        // Get views
        final EditText editText = (EditText) v.findViewById(R.id.CreateID);
        final EditText editText1 = (EditText) v.findViewById(R.id.CreateName);
        final CheckBox checkBox = (CheckBox) v.findViewById(R.id.CreateType);

        builder.setView(v).setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // Get inputted data
                        final String ID = editText.getText().toString();
                        final String name = editText1.getText().toString();
                        final boolean type = checkBox.isChecked();

                        if (!new StringUtil().checkString(ID)) {
                            new ToastMessage(con, con.getString(R.string.group_length_ID), 0).sendToast();
                            return;
                        }

                        if (!new StringUtil().checkString(name)) {
                            new ToastMessage(con, con.getString(R.string.group_length_display), 0).sendToast();
                            return;
                        }

                        new ToastMessage(con, con.getString(R.string.loading), 0).sendToast();

                        final ParseQuery<ParseObject> query = ParseQuery.getQuery("Groups");
                        query.whereEqualTo("ID", ID);
                        query.findInBackground(new FindCallback<ParseObject>() {
                            public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {
                                if (e == null) {
                                    if (parseObjects.size() > 0) {
                                        new ToastMessage(con, con.getString(R.string.group_in_use), 0).sendToast();
                                    } else {
                                        ParseObject addGroup = new ParseObject("Groups");
                                        addGroup.put("Owner", new SettingsManager(con).getString("ID"));
                                        addGroup.put("Display", name);
                                        addGroup.put("ID", ID);
                                        addGroup.put("Private", type);
                                        addGroup.saveInBackground();

                                        new GroupDatabase(con).addGroup(name, ID);
                                        new GroupHandler(con).tempAdd(name, ID);

                                        new ToastMessage(con, con.getString(R.string.group_created), 0).sendToast();
                                    }
                                } else {
                                    new ToastMessage(con, con.getString(R.string.error), 0).sendToast();
                                }
                            }
                        });
                    }
                }

        ).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                }
        );

        builder.create();
        builder.show();
    }
}
