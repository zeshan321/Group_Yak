package me.zeshan.groupyak.Buttons;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.Button;

import me.zeshan.groupyak.Adapters.PostHandler;
import me.zeshan.groupyak.R;
import me.zeshan.groupyak.Util.TabColor;

public class TabListener {

    Context con;
    String group;
    ActionBar actionBar;

    public TabListener(Context con, String group, ActionBar actionBar) {
        this.con = con;
        this.group = group;
        this.actionBar = actionBar;

        tabNew();
        tabHot();
        tabTop();
    }

    private void tabNew() {
        final Button button = (Button) ((Activity)con).findViewById(R.id.page_new);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SwipeListener.swipeLocation = 0;

                actionBar.setTitle("New");
                ((Activity) con).findViewById(R.id.loading).setVisibility(View.VISIBLE);
                new PostHandler(con, group, PostHandler.Type.NEW).initialSetup();

                new TabColor(con, TabColor.Type.NEW).setColor();
            }
        });
    }

    private void tabHot() {
        final Button button = (Button) ((Activity)con).findViewById(R.id.page_hot);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SwipeListener.swipeLocation = 1;

                actionBar.setTitle("Hot");
                ((Activity) con).findViewById(R.id.loading).setVisibility(View.VISIBLE);
                new PostHandler(con, group, PostHandler.Type.HOT).initialSetup();

                new TabColor(con, TabColor.Type.HOT).setColor();
            }
        });
    }

    private void tabTop() {
        final Button button = (Button) ((Activity)con).findViewById(R.id.page_top);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SwipeListener.swipeLocation = 2;

                actionBar.setTitle("Top");
                ((Activity) con).findViewById(R.id.loading).setVisibility(View.VISIBLE);
                new PostHandler(con, group, PostHandler.Type.TOP).initialSetup();

                new TabColor(con, TabColor.Type.TOP).setColor();
            }
        });
    }
}
