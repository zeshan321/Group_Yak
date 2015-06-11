package me.zeshan.groupyak.Dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import me.zeshan.groupyak.Adapters.GroupHandler;
import me.zeshan.groupyak.Databases.GroupDatabase;
import me.zeshan.groupyak.R;

public class JoinGroup {

    Context con;

    public JoinGroup(Context con) {
        this.con = con;

        createDialog();
    }

    private void createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(con);
        LayoutInflater inflater = (LayoutInflater) con.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        final View v = inflater.inflate(R.layout.dialog_joingroup, null);

        builder.setView(v).setPositiveButton("Join", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                EditText editText = (EditText) v.findViewById(R.id.JoinName);
                GroupDatabase groupDatabase = new GroupDatabase(con);

                String name = editText.getText().toString();
                groupDatabase.addGroup(name, name);
                new GroupHandler(con).tempAdd(name, name);
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
