package me.zeshan.groupyak.Util;

import android.app.Activity;
import android.content.Context;
import android.widget.Button;

import me.zeshan.groupyak.R;

public class TabColor {

    Context con;
    Type type;
    Button buttonNew;
    Button buttonHot;
    Button buttonTop;


    public enum Type {
        NEW, HOT, TOP
    }

    public TabColor(Context con, Type type) {
        this.con = con;
        this.type = type;

        this.buttonNew = (Button) ((Activity) con).findViewById(R.id.page_new);
        this.buttonHot = (Button) ((Activity) con).findViewById(R.id.page_hot);
        this.buttonTop = (Button) ((Activity) con).findViewById(R.id.page_top);
    }

    public void setColor() {
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

