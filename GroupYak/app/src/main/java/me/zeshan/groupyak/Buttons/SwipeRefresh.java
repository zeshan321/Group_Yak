package me.zeshan.groupyak.Buttons;

import android.app.Activity;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;

import me.zeshan.groupyak.Adapters.PostHandler;
import me.zeshan.groupyak.R;

public class SwipeRefresh {

    Context con;
    String group;

    public SwipeRefresh(Context con, String group) {
        this.con = con;
        this.group = group;

        swipeListener();
    }

    private void swipeListener() {
        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) ((Activity)con).findViewById(R.id.activity_main_swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(R.color.maincolor, R.color.darkmain);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new PostHandler(con, group).refreshList(swipeRefreshLayout);
            }
        });
    }
}
