package me.zeshan.groupyak.Dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

import me.zeshan.groupyak.R;

public class CreateGroup {

    Context con;

    public CreateGroup(Context con) {
        this.con = con;

        createDialog();
    }

    private void createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(con);
        LayoutInflater inflater = (LayoutInflater) con.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        final View v = inflater.inflate(R.layout.dialog_creategroup, null);

        // EdiText listener
        new CreateListener(con, v);

        builder.setView(v).setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                
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
