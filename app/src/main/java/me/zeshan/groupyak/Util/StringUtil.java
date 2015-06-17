package me.zeshan.groupyak.Util;

import android.text.TextUtils;

public class StringUtil {

    public boolean checkString(String text) {
        if (text.length() < 1 || text.equals("") || text.replace(" ", "").length() < 1 || text.replaceAll("\\s", "").length() < 1 || TextUtils.isEmpty(text) || String.valueOf(text.charAt(0)).equals("") || String.valueOf(text.charAt(0)).equals(" ")) {
            System.out.println("Testingggggg: " + text);
            return false;
        }

        return true;
    }
}
