package me.zeshan.groupyak.Buttons;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import me.zeshan.groupyak.Adapters.PostHandler;
import me.zeshan.groupyak.R;
import me.zeshan.groupyak.Util.OnSwipeTouchListener;
import me.zeshan.groupyak.Util.TabColor;


public class SwipeListener {

    public static int swipeLocation = 0;

    Context con;
    String group;
    ActionBar actionBar;

    public SwipeListener(Context con, String group, ActionBar actionBar) {
        this.con = con;
        this.group = group;
        this.actionBar = actionBar;

        listListener();
    }

    private void listListener() {
        ListView listView = (ListView) ((Activity) con).findViewById(R.id.postList);

        listView.setOnTouchListener(new OnSwipeTouchListener(con) {
            @Override
            public void onSwipeRight() {
                System.out.println("Right: " + swipeLocation);
                if (swipeLocation == 0) {
                    return;
                }
                if (swipeLocation > 0) {
                    if (swipeLocation - 1 >= 0) {
                        swipeLocation--;
                        if (swipeLocation == 0) {
                            actionBar.setTitle("New");
                            ((Activity) con).findViewById(R.id.loading).setVisibility(View.VISIBLE);
                            new PostHandler(con, group, PostHandler.Type.NEW).initialSetup();

                            new TabColor(con, TabColor.Type.NEW).setColor();
                        }
                        if (swipeLocation == 1) {
                            actionBar.setTitle("Hot");
                            ((Activity) con).findViewById(R.id.loading).setVisibility(View.VISIBLE);
                            new PostHandler(con, group, PostHandler.Type.HOT).initialSetup();

                            new TabColor(con, TabColor.Type.HOT).setColor();
                        }
                    }
                }
            }
            @Override
            public void onSwipeLeft() {
                System.out.println("Left: " + swipeLocation);
                if (swipeLocation == 2) {
                    return;
                }
                if (swipeLocation < 2) {
                    if (swipeLocation + 1 <= 2) {
                        swipeLocation++;
                        if (swipeLocation == 1) {
                            actionBar.setTitle("Hot");
                            ((Activity) con).findViewById(R.id.loading).setVisibility(View.VISIBLE);
                            new PostHandler(con, group, PostHandler.Type.HOT).initialSetup();

                            new TabColor(con, TabColor.Type.HOT).setColor();
                        }
                        if (swipeLocation == 2) {
                            actionBar.setTitle("Top");
                            ((Activity) con).findViewById(R.id.loading).setVisibility(View.VISIBLE);
                            new PostHandler(con, group, PostHandler.Type.TOP).initialSetup();

                            new TabColor(con, TabColor.Type.TOP).setColor();
                        }
                    }
                }
            }
        });
    }
}
