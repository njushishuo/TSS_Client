package software.nju.tssclient.util;

import android.util.Base64;

/**
 * Created by ss14 on 2017/6/13.
 */

public class TokenBuilder {


    public static String getToke(String username, String password) {
        String temp = username + ":" + "123";
        String token = "Basic " + Base64.encodeToString(temp.getBytes(), Base64.NO_WRAP);
        return token;
    }
}
