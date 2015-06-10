package me.zeshan.groupyak;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseCrashReporting;
import com.parse.ParseInstallation;

public class ParseSetup extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        com.parse.Parse.enableLocalDatastore(this);
        ParseCrashReporting.enable(this);
        Parse.initialize(this, "9cxYdBzthGSg5tIlFHUtTUU77NPTyVpDXuyARRFC", "nSmV6mj8xWbbPhAmcUEhyhcTvFRTPkTXgg4ZTfD8");
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
