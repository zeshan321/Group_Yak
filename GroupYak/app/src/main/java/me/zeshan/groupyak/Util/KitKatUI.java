package me.zeshan.groupyak.Util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

public class KitKatUI {

    Context con;

    public KitKatUI(Context con) {
        this.con = con;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setNav();
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setNav() {
            Window window = ((Activity)con).getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#303F9F"));
    }
}
