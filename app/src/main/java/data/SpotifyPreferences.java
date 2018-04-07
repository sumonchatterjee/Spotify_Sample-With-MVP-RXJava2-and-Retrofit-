package data;

import android.content.Context;
import android.content.SharedPreferences;

public class SpotifyPreferences {
    public static final String ACCESS_TOKEN = "access_token";
    private static final String PREFS_NAME = "Spotify_Shared_Preference";

    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static void setAcessToken(Context context, String accessToken) {
        getSharedPreferences(context).edit().putString(ACCESS_TOKEN, accessToken).apply();
    }

    public static String getAccessToken(Context context) {
        return getSharedPreferences(context).getString(ACCESS_TOKEN, "");
    }
}
