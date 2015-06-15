package me.zeshan.groupyak.Buttons;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.getbase.floatingactionbutton.FloatingActionButton;

import me.zeshan.groupyak.Dialogs.CreatePost;
import me.zeshan.groupyak.R;

public class PostListener {

    Context con;
    String group;

    public PostListener(Context con, String group) {
        this.con = con;
        this.group = group;

        createListener();
    }

    private void createListener() {
        final FloatingActionButton actionA = (FloatingActionButton) ((Activity) con).findViewById(R.id.post);
        actionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new CreatePost(con, group);
            }
        });
    }
}
