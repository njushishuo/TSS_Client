package software.nju.tssclient.util;

import android.content.SharedPreferences;
import android.util.Base64;

/**
 * Created by ss14 on 2017/6/13.
 */

public class TokenBuilder {


    public static String getToken(SharedPreferences sharedPreferences) {
        String username = sharedPreferences.getString("username", "liuqin");
        String password = sharedPreferences.getString("password", "123");
        String temp = username + ":" + "123";
        String token = "Basic " + Base64.encodeToString(temp.getBytes(), Base64.NO_WRAP);
        return token;
    }
}