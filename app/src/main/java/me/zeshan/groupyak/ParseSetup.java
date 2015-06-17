package me.zeshan.groupyak;

import android.app.Application;
import android.content.Context;

import com.parse.Parse;
import com.parse.ParseCrashReporting;
import com.parse.ParseException;
import com.parse.ParseInstallation;

public class ParseSetup extends Application {

    Context con;

    @Override
    public void onCreate() {
        super.onCreate();
        con = this;

        com.parse.Parse.enableLocalDatastore(con);
        ParseCrashReporting.enable(con);
        Parse.initialize(this, "9cxYdBzthGSg5tIlFHUtTUU77NPTyVpDXuyARRFC", "nSmV6mj8xWbbPhAmcUEhyhcTvFRTPkTXgg4ZTfD8");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SettingsManager settingsManager = new SettingsManager(con);
                    ParseInstallation.getCurrentInstallation().save();

                    if (!settingsManager.contains("ID")) {
                        settingsManager.set("ID", ParseInstallation.getCurrentInstallation().getInstallationId());
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
