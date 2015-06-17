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


public class SwipeListener {

    public static int swipeLocation = 0;

    Context con;
    String group;
    ActionBar actionBar;

    Button buttonNew;
    Button buttonHot;
    Button buttonTop;

    public SwipeListener(Context con, String group, ActionBar actionBar) {
        this.con = con;
        this.group = group;
        this.actionBar = actionBar;

        this.buttonNew = (Button) ((Activity) con).findViewById(R.id.page_new);
        this.buttonHot = (Button) ((Activity) con).findViewById(R.id.page_hot);
        this.buttonTop = (Button) ((Activity) con).findViewById(R.id.page_top);

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

                            setButtonColors(Type.NEW);
                        }
                        if (swipeLocation == 1) {
                            actionBar.setTitle("Hot");
                            ((Activity) con).findViewById(R.id.loading).setVisibility(View.VISIBLE);
                            new PostHandler(con, group, PostHandler.Type.HOT).initialSetup();

                            setButtonColors(Type.HOT);
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

                            setButtonColors(Type.HOT);
                        }
                        if (swipeLocation == 2) {
                            actionBar.setTitle("Top");
                            ((Activity) con).findViewById(R.id.loading).setVisibility(View.VISIBLE);
                            new PostHandler(con, group, PostHandler.Type.TOP).initialSetup();

                            setButtonColors(Type.TOP);
                        }
                    }
                }
            }
        });
    }

    private enum Type {
        NEW, HOT, TOP
    }

    private void setButtonColors(Type type) {
        switch(type) {
            case NEW:
                buttonNew.setBackgroundColor(con.getResources().getColor(R.color.darkmain));

                buttonHot.setBackgroundColor(con.getResources().getColor(R.color.maincolor));
                buttonTop.setBackgroundColor(con.getResources().getColor(R.color.maincolor));
                break;
            case HOT:
                buttonHot.setBackgroundColor(con.getResources().getColor(R.color.darkmain));

                buttonTop.setBackgroundColor(con.getResources().getColor(R.color.maincolor));
                buttonNew.setBackgroundColor(con.getResources().getColor(R.color.maincolor));
                break;

            default:
                buttonTop.setBackgroundColor(con.getResources().getColor(R.color.darkmain));

                buttonHot.setBackgroundColor(con.getResources().getColor(R.color.maincolor));
                buttonNew.setBackgroundColor(con.getResources().getColor(R.color.maincolor));
                break;
        }
    }
}
