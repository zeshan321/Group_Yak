package me.zeshan.groupyak.Buttons;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import me.zeshan.groupyak.Dialogs.CreateGroup;
import me.zeshan.groupyak.Dialogs.JoinGroup;
import me.zeshan.groupyak.R;

public class FloatListeners {

    Context con;

    public FloatListeners(Context con) {
        this.con = con;

        onJoin();
        onCreate();
    }

    private void onJoin() {
        final FloatingActionButton actionA = (FloatingActionButton) ((Activity)con).findViewById(R.id.joinGroup);
        actionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FloatingActionsMenu actionM = (FloatingActionsMenu) ((Activity)con).findViewById(R.id.multiple_actions);
                actionM.collapse();

                new JoinGroup(con);
            }
        });
    }

    private void onCreate() {
        final FloatingActionButton actionA = (FloatingActionButton) ((Activity)con).findViewById(R.id.createGroup);
        actionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FloatingActionsMenu actionM = (FloatingActionsMenu) ((Activity)con).findViewById(R.id.multiple_actions);
                actionM.collapse();

                new CreateGroup(con);
            }
        });
    }
}
